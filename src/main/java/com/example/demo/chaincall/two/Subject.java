package com.example.demo.chaincall.two;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by chenhe
 * @Date 2019-08-15 14:28
 * @Description 优化责任链模式
 */
public abstract class Subject {

    public void execute(){
        this.handler();
    }

    abstract void handler();
}

@Data
class SubjectChain{

    private List<Subject> list = new ArrayList<>();

    private int index = 0;

    public void process(){

        if (index < list.size()){
            list.get(index++).execute();
            this.process();
        }
    }
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

        SubjectChain chain = new SubjectChain();
        List list = chain.getList();
        list.add(part1);
        list.add(part2);
        list.add(part3);

        chain.process();

    }
}
