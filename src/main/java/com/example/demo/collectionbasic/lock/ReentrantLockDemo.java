package com.example.demo.collectionbasic.lock;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chenhe
 * @Date 2019/9/6 10:29
 * @Description
 */
public class ReentrantLockDemo {

    public void testOne(){

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("", "");

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }


    }
}
