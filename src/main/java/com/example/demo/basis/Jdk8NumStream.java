package com.example.demo.basis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author chenhe
 * @Date 2019/8/24 12:08
 * @Description
 */
public class Jdk8NumStream {
    public static void main(String[] args) {
//        System.out.println(range());
//        System.out.println(rangeClose());
        createStreamByFile();



    }

    public static Long range(){
        //range(i1, i2) 不包含i2
        return IntStream.range(1, 100)
                .filter(item->item % 2 ==0)
                .count();
    }

    public static Long rangeClose(){
        return IntStream.rangeClosed(1, 100)
                .filter(item-> item%2 == 0)
                .count();
    }

    //数值生成流
    public static void crateSteam(){
        Stream.of("java 8", "php", "vue", "Action");
    }

    //数组生成流
    public static void crateStreamByArray(){
        String [] arr = {"java 8", "php", "vue", "Action"};
        long count = Arrays.stream(arr).count();
    }
//文件生成流
    public static void createStreamByFile(){
        try {
            Stream<String> fileSteam = Files.lines(Paths.get(
                    "D:/codeDemo/mybatis-plus-demo/src/main/java/com/example/demo/basis/readme.txt"),
                    Charset.defaultCharset());
            fileSteam.distinct()
                    .forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
