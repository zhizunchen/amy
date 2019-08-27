package com.example.demo.basis.future;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Created by chenhe
 * @Date 2019-08-26 13:23
 * @Description  jdk 1.8 CompletableFuture
 */
public class CompletableFutureTest {

    public static void main(String[] args) {

        //1
        CompletableFutureTest test = new CompletableFutureTest();
        Future<Double> price = test.getPriceAsync("12");
        try {
            System.out.println(price.get(2, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {}


    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> future = new CompletableFuture<>();//创建COmpletableFuture 实例对象
        new Thread(()->{
            try {
                double price = doSomeLongComputation(); //线程执行任务
                future.complete(price); //返回执行单
            } catch (Exception e) {
                future.completeExceptionally(e);//线程异常抛出到主线程  抛出ExecutionException
                System.out.println("1234567890-" + e.getMessage());
            }
        }).start();
        return future;

//        return CompletableFuture.supplyAsync(()->doSomeLongComputation());
    }

    private static Double doSomeLongComputation() {
        try {
            Thread.sleep(1000);
            System.out.println("======= doSomeLongComputation ======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0.0;
    }





}
