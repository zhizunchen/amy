package com.example.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Created by chenhe
 * @Date 2019-08-28 14:26
 * @Description 创建Znode
 */
public class ZKCreate {

    private static ZooKeeper zk;

    private static ZooKeeperConnection conn;

    //create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
//    path - Znode路径。例如，/myapp1，/myapp2，/myapp1/mydata1，myapp2/mydata1/myanothersubdata
//    data - 要存储在指定znode路径中的数据
//    acl - 要创建的节点的访问控制列表。ZooKeeper API提供了一个静态接口 ZooDefs.Ids 来获取一些基本的acl列表。例如，ZooDefs.Ids.OPEN_ACL_UNSAFE返回打开znode的acl列表
//    createMode - 节点的类型，即临时，顺序或两者。这是一个枚举。
    public static void create(String path, byte[] data) throws KeeperException,InterruptedException {
        String info = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("============== " + info);
    }

//    exists(String path, boolean watcher)  检查Znode的存在
//    path- Znode路径
//    watcher - 布尔值，用于指定是否监视指定的znode
    public static Stat znode_exists(String path) throws KeeperException,InterruptedException {
        return zk.exists(path, true);
    }

//    getData(String path, Watcher watcher, Stat stat)  获取附加在指定znode中的数据及其状态
//    path - Znode路径。
//    watcher - 监视器类型的回调函数。当指定的znode的数据改变时，ZooKeeper集合将通过监视器回调进行通知。这是一次性通知。
//    stat - 返回znode的元数据。
    public static byte[] getData(String znodePath) throws KeeperException,InterruptedException{
        return zk.getData(znodePath, (WatchedEvent watchedEvent)->{},null);
    }

//    setData(String path, byte[] data, int version)
//    path- Znode路径
//    data - 要存储在指定znode路径中的数据。
//    version- znode的当前版本。每当数据更改时，ZooKeeper会更新znode的版本号
    public static Stat setData(String znodePath, byte[] buf, int version)throws KeeperException,InterruptedException{
        return zk.setData(znodePath, buf, version);
    }

    public static void main(String[] args) {

        String znodePath = "/myFirstZnode";

        byte[] data = "my first zookeeper test".getBytes();

        try {

            conn = new ZooKeeperConnection();

            zk = conn.connect("127.0.0.1");
            System.out.println("会话ID ：" + zk.getSessionId());

            Stat state = znode_exists(znodePath);

            if(null != state){

                System.out.println("znode已经存在 version=" + state.getAversion());

                byte[] b = getData(znodePath);

                String info = new String(b, "UTF-8");

                System.out.println("获取到的数据：" + info);

                //更新数据
                Stat newStat = setData(znodePath, "the new Info".getBytes(), state.getVersion());

                System.out.println(newStat.getAversion());
            }else {

                create(znodePath, data);//创建znode 创建子目录 可以存储数据
            }
        } catch (Exception e) {}

        StringBuffer buf = new StringBuffer();
        StringBuilder builder = new StringBuilder();

    }

}
