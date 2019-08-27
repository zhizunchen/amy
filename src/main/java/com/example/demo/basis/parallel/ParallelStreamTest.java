package com.example.demo.basis.parallel;

import java.util.Date;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Created by chenhe
 * @Date 2019-08-26 15:40
 * @Description parallelStream 并行流
 *
 * 并行操作注意
 * 1 方法是否支持 易并行
 * 2 共享变量状态
 */
public class ParallelStreamTest {

    private static long javaSum(long n){
        long result = 0l;
        for (int i = 1; i<=n; i++){
            result += i;
        }
        return result;
    }

    //顺序流
    private static long sequentialSum(long n){
        return Stream.iterate(1l, i->i+1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    //并行流
    private static long sum(long n){
        //iterate 生成的是装箱的对象，必须拆箱成数字才能求和
        //不易并行化的操作 消耗更多性能
        return Stream.iterate(1l, i->i+1)
                .limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }

    //顺序流
    private static long rangeSum(long n){
        return LongStream.rangeClosed(1, n)
                .reduce(0l, Long::sum);
    }
    //并行流
    private static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0l, Long::sum);
    }



    public static void main(String[] args) {

        //并行操作在多个内核之间移动数据的代价消耗较大

        Long startTime = new Date().getTime();
//        sequentialSum(10000000);
//        sum(10000000);

//        System.out.println(rangeSum(100));
        System.out.println(parallelRangedSum(20));   //数值不等于5050 多线程 共享变量不安全total += value 非院子操作

        System.out.println("耗时：" + (new Date().getTime() - startTime));
    }



}
