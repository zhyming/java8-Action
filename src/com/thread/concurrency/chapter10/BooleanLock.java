package com.thread.concurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 22:46
 */
public class BooleanLock implements Lock  {

    //定义标记，锁是否已经被其它线程拿走，false--表明已经被拿走则等待；true--表明有锁，可以去抢锁
    //是否有锁
    private boolean hasLock;

    //存放阻塞的线程
    private Collection<Thread> blockedThreads = new ArrayList<>();

    //记录当前获取锁的线程
    private Thread currentThread;

    public BooleanLock(){

        this.hasLock = true;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (!hasLock){
            blockedThreads.add(Thread.currentThread());
            this.wait();
        }
        currentThread = Thread.currentThread();
        blockedThreads.remove(currentThread);
        this.hasLock = false;

    }

    @Override
    public synchronized void lock(long millis) throws InterruptedException, TimeOutException {
        if (millis <= 0)
            lock();
        long begin = System.currentTimeMillis();
        long useTime = 0;
        while (!hasLock){
            if (useTime  > millis)
                throw new TimeOutException("超时了");
            blockedThreads.add(Thread.currentThread());
            this.wait(millis);
            useTime = System.currentTimeMillis() - begin;
        }

        currentThread = Thread.currentThread();
        blockedThreads.remove(currentThread);
        this.hasLock = false;
    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread){
            this.hasLock = true;
            Optional.of(Thread.currentThread().getName() + "  release the lock monitor.").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreads);
    }

    @Override
    public int getBlockThreadSize() {
        return blockedThreads.size();
    }
}
