package com.example.demo.basis.two;

import lombok.Data;

/**
 * @Created by chenhe
 * @Date 2019-08-23 16:51
 * @Description Jdk8StreamTest 交易类
 */
@Data
public class Transaction {

    private final Trader trader;

    private final int year;

    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
