package com.example.demo.collectionbasic.StringDemo;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.util.Arrays;

/**
 * @Author chenhe
 * @Date 2019/9/9 13:37
 * @Description
 */
public class StringTwo {

    //将一个字符串中的每个空格替换成“%20
    //1
    public void replaceSpace(String param){
        int len = param.length();
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i < len ; i++){
            char p = param.charAt(i);
            if(String.valueOf(p).equals(" ")){
                sub.append("%20");
            }else {
                sub.append(p);
            }
        }
        sub.toString();
    }
    //2
    public void replaceSpaceTwo(String param){
        param.replace("\\s", "%20");
    }

    //3. 最长公共前缀
    // 1
    public void mainFirst(String [] arr){
        int len;
        if(null == arr || 0 == (len = arr.length)){
            throw new IllegalArgumentException("=====");
        }
        //
        Arrays.sort(arr);

        int m = arr[0].length();
        int n = arr[len - 1].length();
        int num = Math.min(m, n);
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i<num; i++){
            if(arr[0].charAt(i) == arr[len - 1].charAt(i)){
                sub.append(arr[0].charAt(i));
                continue;
            }else{
                break;
            }
        }
        sub.toString();
    }

    //验证字符串是否为回文串
    //1
    public void isPalindrome(String param){
        if(StringUtils.isBlank(param)) {
            throw new IllegalArgumentException("======");
        }
        int h = 0;
        int t =  param.length() - 1;
        Boolean bol = true;
        while (h < t){
            if(!Character.isLetterOrDigit(param.charAt(h))){
                h++;
            }else if(!Character.isLetterOrDigit(param.charAt(t))){
                t--;
            }else{
                if(param.charAt(h) != param.charAt(t)){
                    bol = false;
                }
                h++;
                t--;
            }
        }
    }

    //最长回文子串  最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。
    //1
    public void longestPalindrome(String param){
        int len ;
        if(StringUtils.isBlank(param) || (len = param.length()) < 2){
            throw new IllegalArgumentException("=====不符合刷选条件");
        }

        char[] arr = param.toCharArray();
        boolean isFinded = false;
        for (int i = 0; i< len - 1; i++){
            for (int j = 0; j<=i; j++){
                int endIndex = len -1 - i;
                if (arr[j] == arr[endIndex + j]){
                    int start = j;
                    int end = endIndex + j;
                    while(start<=end){
                        if(arr[start] != arr[end]){
                            break;
                        }
                        start++;
                        end--;
                    }
                    if (arr[start] == arr[end]){
                        isFinded = true;
                        System.out.println("============="+param.substring(j, endIndex + j + 1));
                        break;
                    }
                }
            }
            if(isFinded){
                break;
            }
        }
    }

    //括号匹配深度
    //括号匹配深度
    public void maxLength(String param){
        if(StringUtils.isBlank(param)){
            System.out.println("===========" + 1);
        }
        char [] arr = param.toCharArray();
        int count = 0;
        int max = 0;
        for (int i = 0; i< arr.length; i++){
            if(String.valueOf(arr[i]).equals("(")){
                count++;
            }else{
                count--;
            }
            max = Math.max(max, count);
        }
        System.out.println("========max" + max);
    }

    public static void main(String[] args) {
        StringTwo two = new StringTwo();
//        two.longestPalindrome("12qwertyytrewq");
        two.maxLength("()()(()(()))");
    }
}
