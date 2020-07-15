package com.thread.concurrency.chapter9;

import java.util.*;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 1:53
 */
public class CaptureService {

    final static private LinkedList<Control> CONTROLS = new LinkedList<>();
    private final static int MAX = 5;
    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();


        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream()
                .map(CaptureService::createThread)
                .forEach(n ->
                {
                    n.start();
                    worker.add(n);
                });
        worker.stream().forEach(n ->
        {
            try {
                n.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } );

        Optional.of("All of tht capture worker finish").ifPresent(System.out::println);
    }

    private static Thread createThread(String name){
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "] begin capture data")
                    .ifPresent(System.out::println);
                synchronized (CONTROLS){
                    while (CONTROLS.size() > MAX){
                        try {
                            CONTROLS.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    CONTROLS.addLast(new Control());
                }


                Optional.of("The worker [" + Thread.currentThread().getName() + "] is working。。。")
                        .ifPresent(System.out::println);

                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized(CONTROLS){
                    Optional.of("The worker [" + Thread.currentThread().getName() + "] end capture work")
                            .ifPresent(System.out::println);

                    CONTROLS.removeFirst();
                    CONTROLS.notifyAll();
            }
        },name);
    }

    private static class Control{
    }
}
