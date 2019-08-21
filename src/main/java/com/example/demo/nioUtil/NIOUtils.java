package com.example.demo.nioUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class NIOUtils {

    public static void main(String[] args) {
        NIOUtils util = new NIOUtils();
//        util.readByChannel("/Users/lsjr3/chenhe/p2p-code/chenhe-code/test.txt");


//        util.readByIO(new File(File.separator + "Users" + File.separator + "lsjr3" + File.separator + "chenhe" + File.separator + "p2p-code" + File.separator + "chenhe-code" + File.separator + "test.txt"),
//                new File(File.separator + "Users" + File.separator + "lsjr3" + File.separator + "chenhe" + File.separator + "p2p-code" + File.separator + "chenhe-code" + File.separator + "test1.txt"));

        Long start = new Date().getTime();
//        util.readByChannel(File.separator + "Users" + File.separator + "lsjr3" + File.separator + "chenhe" + File.separator + "p2p-code" + File.separator + "chenhe-code" + File.separator + "test.txt",
//                File.separator + "Users" + File.separator + "lsjr3" + File.separator + "chenhe" + File.separator + "p2p-code" + File.separator + "chenhe-code" + File.separator + "test1.txt");

        util.readByChannel("/Users/lsjr3/Downloads/Git-2.12.2-64-bit.exe", "Git-123456789.exe");

        System.out.println(new Date().getTime() - start);

    }

    public void readByIO(File file, File descFile){
        if(!file.exists()){
            System.out.println("文件不存在");
        }
        if(!file.isFile()){
            System.out.println("文件不存在");
        }

        try {

            FileInputStream fis = new FileInputStream(file);

            FileOutputStream fos = new FileOutputStream(descFile);

            int len = 0;

            byte [] buf = new byte[100];
            while((len = fis.read(buf)) != -1){
                fos.write(buf, 0 , len);
            }
            fos.flush();
            fos.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){}

    }

    public void readByChannel(String fileurl, String descFile){
        try {

            if(!new File(fileurl).exists()){
                System.out.println(" not exist...");
            }
            if(!new File(fileurl).isFile()){
                System.out.println("is not file");
            }

            RandomAccessFile raf = new RandomAccessFile(fileurl, "r");
            //连接到文件的通道  运行在阻塞模式
            FileChannel channel = raf.getChannel();

            //ByteBuffer
            ByteBuffer buf = ByteBuffer.allocate(100);

            //
            RandomAccessFile descraf = new RandomAccessFile(descFile, "rw");

            FileChannel descchannel = descraf.getChannel();

            int limit = -1;

            while ((limit = channel.read(buf)) != -1){
                //buf 从写模式切换读模式
                buf.flip();

                descchannel.write(buf);

                //清空buf 重新写入数据  or compact
                buf.clear();
            }

            channel.close();
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){}


    }

}
