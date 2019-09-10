package com.example.demo.collectionbasic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author chenhe
 * @Date 2019/9/4 14:23
 * @Description
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {

    }
}
class ThreadDemo implements Runnable{

    private ThreadLocal local;

    public ThreadDemo(ThreadLocal local) {
        this.local = local;
        local.get();
        local.set("qwertyu");
    }

    @Override
    public void run() {
        System.out.println(local.get().hashCode());
    }
}






