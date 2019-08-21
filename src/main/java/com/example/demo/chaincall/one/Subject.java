package com.example.demo.chaincall.one;

import lombok.Data;

/**
 * @Created by chenhe
 * @Date 2019-08-15 14:10
 * @Description 主体 基本的责任链模式
 */
@Data
public abstract class Subject {

    private Subject successor;

    public void execute(){
        handler();
        if(null != successor){
            successor.execute();
        }
    }

    abstract void handler();

}
class MainExec{

    private static class Part1 extends Subject{
        @Override
        void handler() {
            System.out.println("========part1");
        }
    }
    private static class Part2 extends Subject{
        @Override
        void handler() {
            System.out.println("========part2");
        }
    }
    private static class Part3 extends Subject{
        @Override
        void handler() {
            System.out.println("========part3");
        }
    }

    public static void main(String[] args) {
        Subject part1 = new Part1();
        Subject part2 = new Part2();
        Subject part3 = new Part3();

        part1.setSuccessor(part2);
        part2.setSuccessor(part3);

        part1.execute();
    }

}