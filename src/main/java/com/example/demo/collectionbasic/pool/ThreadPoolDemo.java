package com.example.demo.collectionbasic.pool;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author chenhe
 * @Date 2019/9/5 16:22
 * @Description
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolDemo demo = new ThreadPoolDemo(2, 2, new ArrayBlockingQueue(4));
       for (int i = 0; i < 10; i++) {
           demo.submit(new Runnable() {
               @Override
               public void run() {
                   System.out.println("子线程调用=====");
               }
           });
       }
    }

    private int corePoolSize;

    private int maximumPoolSize;

    private Queue queue;//队列

    private int activePoolSize;

    private ThreadFactory threadFactory;

    public ThreadPoolDemo(int corePoolSize, int maximumPoolSize, Queue queue){
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.queue = queue;
        threadFactory = new ThreadFactory(queue);
    }

    public void submit(Runnable runnable){
        if(null == runnable) {
            throw new IllegalArgumentException("任务为空");
        }

        synchronized(ThreadPoolDemo.class){
            if(activePoolSize < corePoolSize){
                activePoolSize++;
                threadFactory.newThread(runnable);
                return;
            }
        }

        if(!queue.add(runnable)){
            synchronized (ThreadPoolDemo.class) {
                if (activePoolSize < maximumPoolSize) {
                    activePoolSize++;
                    threadFactory.newThread(runnable);
                    return;
                }
            }
            throw new IllegalArgumentException("队列已满 不接受新任务");
        }
    }
}
class ThreadFactory{

    private Queue<Runnable> queue;

    public ThreadFactory(Queue queue){
        this.queue = queue;
    }

    public void newThread(Runnable runnable){
        Worker worker = new Worker(runnable);
        Thread t = worker.thread;
        t.start();
    }
    class Worker implements Runnable{
        private Runnable firstTask;

        private Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            firstTask = null;//初始任务首次执行完后清除
            while (null != task || (task = queue.poll()) != null){
                try {
                    task.run();
                }finally {
                  task = null;//task执行完清空 不影响while循环
                }
            }
        }
    }
}
