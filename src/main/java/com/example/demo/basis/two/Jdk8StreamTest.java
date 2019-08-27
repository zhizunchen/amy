package com.example.demo.basis.two;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

/**
 * @Created by chenhe
 * @Date 2019-08-23 16:47
 * @Description JDk 8 stream 流操作
 */
public class Jdk8StreamTest {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

//        one(transactions).forEach(System.out::println);
//        two(transactions).forEach(System.out::println);
//        three(transactions).forEach(System.out::println);
//        four(transactions).forEach(System.out::println);
        System.out.println(five(transactions));


    }
//    (1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
    public static List<Transaction> one(List<Transaction> transactions){
        return transactions.stream()
                .filter(item -> item.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

//    (2) 交易员都在哪些不同的城市工作过?
    public static List<String> two(List<Transaction> transactions){
//        return transactions.stream()
//                .map(Transaction::getTrader)
//                .map(Trader::getCity)
//                .distinct()
//                .collect(Collectors.toList());

        return transactions.stream()
                            .map(item->item.getTrader().getCity())
                            .distinct()
                            .collect(Collectors.toList());
    }

//    (3) 查找所有来自于剑桥的交易员，并按姓名排序
    public static List<Trader> three(List<Transaction> transactions){
        return transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(item->"Cambridge".equals(item.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

//    (4) 返回所有交易员的姓名字符串，按字母顺序排序。
    public static List<String> four(List<Transaction> transactions){
        return transactions.stream()
                .map(item->item.getTrader().getName())
                .distinct()
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());

    }
//    (5) 有没有交易员是在米兰工作的?
    public static boolean five(List<Transaction> transactions){
        return transactions.stream()
                .anyMatch(item->"Milan".equals(item.getTrader().getCity()));
    }
//(6) 打印生活在剑桥的交易员的所有交易额。
    public static void six(List<Transaction> transactions){
        transactions.stream()
                .filter(item->"Cambridge".equals(item.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }
//    (7) 所有交易中，最高的交易额是多少?
    public static void seven(List<Transaction> transactions){
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }
//    (8) 找到交易额最小的交易
    public static void eight(List<Transaction> transactions){
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }

    public static Transaction getMax(List<Transaction> transactions){
        return transactions.stream()
                .collect(maxBy(Comparator.comparing(Transaction::getValue))) //收集器
                .get();
    }

}





















