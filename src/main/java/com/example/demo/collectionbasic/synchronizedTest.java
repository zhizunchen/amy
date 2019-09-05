package com.example.demo.collectionbasic;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.sql.SQLOutput;

/**
 * @Author chenhe
 * @Date 2019/9/3 16:36
 * @Description synchronized Test
 */
public class synchronizedTest {

    public static void main(String[] args) {
        CodeBlock code1 = new CodeBlock();
        CodeBlock code2 = new CodeBlock();
        BlockThreadOne block = new BlockThreadOne(code1);
        BlockThread block1 = new BlockThread(code2);
        new Thread(block, "thread1").start();
        new Thread(block1, "thread2").start();
    }

}
class BlockThreadOne implements Runnable{
    public CodeBlock block;
    public BlockThreadOne(CodeBlock block) {
        this.block = block;
    }

    @Override
    public void run() {
//        block.test();
        block.test1();
    }
}
class BlockThread implements Runnable{
    public CodeBlock block;
    public BlockThread(CodeBlock block) {
        this.block = block;
    }

    @Override
    public void run() {
//        block.test2();
        block.test();
    }
}
//代码块
class CodeBlock {

    private static int count;

    public static synchronized void test(){
        for (int i=0; i<5; i++){
            try {
                System.out.println("test==" + Thread.currentThread().getName()+":"+count++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void test1(){
        for (int i=0; i<5; i++){
            try {
                System.out.println("test1" + Thread.currentThread().getName()+":"+count++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void test2(){
        for (int i=0; i<5; i++){
            try {
                System.out.println("test2" + Thread.currentThread().getName()+":"+count++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//class SynchronizedTest {
//    public void synMethod0(){
//        synchronized (this){
//        }
//    }
//    public synchronized void synMethod1(){
//    }
//    public static synchronized void synMethod2(){
//    }
//}

//public class SynchronizedTest
//    minor version: 0
//        major version: 52
//        flags: ACC_PUBLIC, ACC_SUPER
//        Constant pool:
//        #1 = Methodref #3.#17 // java/lang/Object."<init>":()V
//        #2 = Class #18 // SynchronizedTest
//        #3 = Class #19 // java/lang/Object
//        #4 = Utf8 <init>
//    #5 = Utf8 ()V
//            #6 = Utf8 Code
//            #7 = Utf8 LineNumberTable
//            #8 = Utf8 synMethod0
//            #9 = Utf8 StackMapTable
//            #10 = Class #18 // SynchronizedTest
//            #11 = Class #19 // java/lang/Object
//            #12 = Class #20 // java/lang/Throwable
//            #13 = Utf8 synMethod1
//            #14 = Utf8 synMethod2
//            #15 = Utf8 SourceFile
//            #16 = Utf8 SynchronizedTest.java
//            #17 = NameAndType #4:#5 // "<init>":()V
//            #18 = Utf8 SynchronizedTest
//            #19 = Utf8 java/lang/Object
//            #20 = Utf8 java/lang/Throwable
//            {
//public SynchronizedTest();
//        deor: ()V
//        flags: ACC_PUBLIC
//        Code:
//        stack=1, locals=1, args_size=1
//        0: aload_0
//        1: invokespecial #1 // Method java/lang/Object."<init>":()V
//        4: return
//        LineNumberTable:
//        line 2: 0
//public void synMethod0();
//        deor: ()V
//        flags: ACC_PUBLIC
//        Code:
//        stack=2, locals=3, args_size=1
//        0: aload_0
//        1: dup
//        2: astore_1
/**monitorenter //插入同步代码块的开始位置 */
/**monitorexit  //插入同步代码块的结束位置 */
//        3: monitorenter
//        4: aload_1
//        5: monitorexit
//        6: goto 14
//        9: astore_2
//        10: aload_1
//        11: monitorexit
//        12: aload_2
//        13: athrow
//        14: return
/**Exception table // 列出方法中抛出的受检异常 */
//        Exception table:
//        from to target type
//        4 6 9 any
//        9 12 9 any
//        LineNumberTable:
//        line 5: 0
//        line 7: 4
//        line 8: 14
//        StackMapTable: number_of_entries = 2
//        frame_type = 255 /* full_frame */
//        offset_delta = 9
//        locals = [ class SynchronizedTest, class java/lang/Object ]
//        stack = [ class java/lang/Throwable ]
//        frame_type = 250 /* chop */
//        offset_delta = 4
//public synchronized void synMethod1();
//        deor: ()V
/**ACC_SYNCHRONIZED //标记方法是否是同步方法 */
//        flags: ACC_PUBLIC, ACC_SYNCHRONIZED
//        Code:
//        stack=0, locals=1, args_size=1
//        0: return
//        LineNumberTable:
//        line 13: 0
//public static synchronized void synMethod2();
//        deor: ()V
//        flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
//        Code:
//        stack=0, locals=0, args_size=0
//        0: return
//        LineNumberTable:
//        line 17: 0
//        }
