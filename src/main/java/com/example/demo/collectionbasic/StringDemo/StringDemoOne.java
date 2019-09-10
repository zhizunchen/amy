package com.example.demo.collectionbasic.StringDemo;

import org.apache.commons.lang.StringUtils;

/**
 * @Author chenhe
 * @Date 2019/9/9 11:18
 * @Description
 * 一个字符串（S）中查找一个子串（W）出现的位置
 */
public class StringDemoOne {

    public static void main(String[] args) {
        String original = "qwertyuiop";
        String target = "io";
        System.out.println(findPosition(original, target));
    }

    // 1
    public static int findPosition(String original, String target){
        if(StringUtils.isBlank(original) || StringUtils.isBlank(target)){
            throw new IllegalArgumentException("======");
        }
        int tail = target.length();
        int len = original.length();
        for (int header = 0; tail< len; header++, tail++){
            String temp = original.substring(header, tail);
            if(temp.equals(target)){
                return header;
            }
        }
        return 0;
    }
}
