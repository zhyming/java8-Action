package com.thread.concurrency.chapter10;

import java.util.Collection;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 22:38
 */
public interface Lock {

    class TimeOutException extends Exception{

        public TimeOutException(String msg){
            super(msg);
        }

    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockThreadSize();
}
