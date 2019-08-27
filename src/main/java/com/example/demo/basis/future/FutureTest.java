package com.example.demo.basis.future;

import java.util.concurrent.*;

/**
 * @Created by chenhe
 * @Date 2019-08-26 11:20
 * @Description future test   主线程与线程数据交互
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            public Double call(){
                return doSomeLongComputation();
            }
        });
        doSomethingElse();

        //线程是否执行完毕
        while (!future.isDone()){
            try {
                System.out.println("=====  线程未执行完  等待中 ==");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
//            future.get();  一直等待执行结果 否则永无止境的等待下去

            Double d =  future.get(1, TimeUnit.SECONDS); //Timeout-Exception
            System.out.println("=======  线程执行结果 " + d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doSomethingElse() {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "======== doSomethingElse ======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Double doSomeLongComputation() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "======= doSomeLongComputation ======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
