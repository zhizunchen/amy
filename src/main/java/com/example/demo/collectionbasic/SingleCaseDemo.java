package com.example.demo.collectionbasic;

import sun.management.jmxremote.SingleEntryRegistry;

/**
 * @Author chenhe
 * @Date 2019/9/4 10:52
 * @Description
 */
public class SingleCaseDemo {
//    private static SingleCaseDemo instance;
    private volatile static SingleCaseDemo instance;

    protected SingleCaseDemo(){}
    //1 多线程下可能创建多个实例对象
//    public static SingleCaseDemo getInstance(){
//        if(null == instance){
//            instance = new SingleCaseDemo();
//        }
//        return instance;
//    }

    //2 线程每次获取 实例对象都要排队
//    public synchronized static SingleCaseDemo getInstance(){
//        if(null == instance){
//            instance = new SingleCaseDemo();
//        }
//        return instance;
//    }
    //3 首次实例化对象 多线程条件下 排队
    public static SingleCaseDemo getInstance(){
        if(null == instance){
            synchronized (SingleCaseDemo.class){
                if(null == instance){
                    instance = new SingleCaseDemo();
                }
            }
        }
        return instance;
    }

}

class SingleDemo{
    private static SingleDemo instance = new SingleDemo();

    public static SingleDemo getInstance(){
        return instance;
    }
}
