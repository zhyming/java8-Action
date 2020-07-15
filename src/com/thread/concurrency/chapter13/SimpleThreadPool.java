
package com.thread.concurrency.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/9 22:29
 */
public class SimpleThreadPool extends Thread{

    private int size;

    private final int queueSize;

    private static volatile int seq = 0;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");


    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final DiscardPolicy discardPolicy;
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard this task.");
    };

    private volatile boolean isdestory = false;

    private int min;

    private int max;

    private int active;

    public SimpleThreadPool(){
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }
    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy){
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init(){
        for (int i = 0; i < min; i ++){
            createWorkTask();
        }

        this.size = min;
        this.start();
    }

    public void submit(Runnable runnable){
        if (isdestory){
            throw new IllegalStateException("The thread pool already destory and not allow submit task.");
        }
        synchronized(TASK_QUEUE){
            if(TASK_QUEUE.size() > queueSize)
                discardPolicy.discard();
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while(!isdestory){
            System.out.printf("Pool#Min:%d, Active:%d, Max:%d, Threads:%d, Tasks:%d\n"
                    , min, active, max, size, TASK_QUEUE.size());

            try {
                Thread.sleep(5_000);

                if (TASK_QUEUE.size() > active && size < active){
                    for(int i = size; i < active; i++){
                        createWorkTask();
                    }
                    System.out.println("The pool size increamed To active.");
                    size = active;
                }else if (TASK_QUEUE.size() > max && size < max){
                    for(int i = size; i < max; i++){
                        createWorkTask();
                    }
                    System.out.println("The pool size increamed To Max.");
                    size = max;

                }
                if (TASK_QUEUE.isEmpty() && size > min){

                    System.out.println("Reduce thread.=============");
                    synchronized (THREAD_QUEUE){
                        int releaseSize = size - min;
                        int cout = 0;
                        for(Iterator<WorkerTask> it= THREAD_QUEUE.iterator(); it.hasNext();){
                            if (releaseSize <= 0){
                                //size = min;
                                break;
                            }
                            WorkerTask workerTask = it.next();
                            if (workerTask.taskState == TaskState.BLOCKED){
                                cout ++;
                                workerTask.close();
                                workerTask.interrupt();
                                it.remove();
                                releaseSize --;
                            }
                        }
                        if (cout == releaseSize){
                            size = min;
                        }else {
                            size -= cout;
                        }


                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkTask(){
        WorkerTask workerTask = new WorkerTask(GROUP, THREAD_PREFIX + seq ++);
        workerTask.start();
        THREAD_QUEUE.add(workerTask);
    }

    public static class DiscardException extends RuntimeException{
        public DiscardException(String msg){
            super(msg);
        }
    }

    interface DiscardPolicy{
        void discard() throws DiscardException;
    }

    public void shutdown(){
        while (!TASK_QUEUE.isEmpty()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        int activeCout = THREAD_QUEUE.size();

        while (activeCout > 0){
            for (WorkerTask thread : THREAD_QUEUE){
                if (thread.taskState == TaskState.BLOCKED){
                    thread.close();
                    thread.interrupt();
                    activeCout --;
                }else{
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        this.isdestory = true;
        System.out.println("The thread pool disposed.");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean getIsdestory(){
        return this.isdestory;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    private enum TaskState{
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkerTask extends Thread{

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup threadGroup, String name){
            super(threadGroup, name);
        }

        public TaskState getTaskState(){
            return this.taskState;
        }

        public void run(){
            OUTO:
            while(this.taskState != TaskState.DEAD){
                Runnable runnable = null;
                synchronized(TASK_QUEUE){

                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTO;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }

    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();

        IntStream.range(0, 40).forEach(
                i -> simpleThreadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "working...");
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "done.");
                })
        );



        /*try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleThreadPool.shutdown();
        simpleThreadPool.submit(() -> {
            System.out.println("================");
        });*/

    }


}
