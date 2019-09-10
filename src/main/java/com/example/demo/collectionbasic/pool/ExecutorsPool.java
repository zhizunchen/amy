package com.example.demo.collectionbasic.pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.*;

/**
 * @Author chenhe
 * @Date 2019/9/5 14:05
 * @Description
 */
public class ExecutorsPool {

    public void getPool(){

        //创建线程池不使用Executors 而是通过ThreadPoolExecutor的方法，明确线程池的运行规则，规避资源耗尽风险
        // 1 ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 8,
                2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池添加任务 submit可以接收返回的值 但Runable 无返回值 Future 接收到null");
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池添加任务 无返回值");
            }
        });
        executor.submit(new Callable<Object>() {
            public String call(){
                return "线程池添加任务 有返回值 通过Future传递";
            }
        });


        // 2 Executors
        Executors.newCachedThreadPool();

        // 3 spring支持的线程池 ThreadPoolTaskExecutor
        ThreadPoolTaskExecutor springPool = new ThreadPoolTaskExecutor();
        springPool.setCorePoolSize(6);
        springPool.setMaxPoolSize(8);
        springPool.setKeepAliveSeconds(2);
        springPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        springPool.submit(new Callable<Object>() {
            public String call() throws Exception{
                return "";
            }
        });



    }
}
