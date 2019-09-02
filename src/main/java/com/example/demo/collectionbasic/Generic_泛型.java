package com.example.demo.collectionbasic;

/**
 * @Created by chenhe
 * @Date 2019-08-30 09:44
 * @Description 一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
 */
public class Generic_泛型 {

    public static <T extends Number & Comparable<? super T>> T min(T[] values){
        if(null == values || values.length == 0){
            return null;

        }
        T min = values[0];
        for(int i = 1; i < values.length; i++){
            if(min.compareTo(values[i]) > 0){
                min = values[i];
            }
        }
        return min;
    }
}
