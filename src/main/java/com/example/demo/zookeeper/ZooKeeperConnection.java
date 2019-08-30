package com.example.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Created by chenhe
 * @Date 2019-08-28 14:19
 * @Description zookeeper 连接
 */
public class ZooKeeperConnection {

    private ZooKeeper zoo;


    final CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException,InterruptedException{
        zoo = new ZooKeeper(host, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent we) {
                if(we.getState() == Event.KeeperState.SyncConnected){
                    connectedSignal.countDown();//停止（等待）主进程，直到客户端与ZooKeeper集合连接
                }
            }
        } //client注册监听关心的目录结点 当目录结点发生变化时，zookeeper会通知客户端
        );
        connectedSignal.await();
        return zoo;
    }

    public void close() throws InterruptedException {
        zoo.close();
    }
}
// 集群 所有机器在父目录下创建临时目录节点，监听父目录结点的子节点变化消息，机器挂断，断开zk连接，临时目录结点被删除，其他机器收到通知
//


