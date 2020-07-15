package com.thread.concurrency.chapter8;

import org.omg.CORBA.ObjectHelper;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/3 23:12
 */
public class DeadLock {

    private  OtherService otherService;

    private final Object LOCK = new Object();

    public DeadLock(OtherService otherService){
        this.otherService = otherService;
    }

    public void m1(){
        synchronized (LOCK){
            System.out.println("m1===========");
        }
    }

    public void m2(){
        synchronized (LOCK){
            System.out.println("m2===========");
            otherService.s2();
        }
    }
}
