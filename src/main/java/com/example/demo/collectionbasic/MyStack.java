package com.example.demo.collectionbasic;

import java.util.Arrays;

/**
 * @Created by chenhe
 * @Date 2019-08-30 10:04
 * @Description 使用数组实现栈 先进后出
 */
public class MyStack {

    private int[] storage;

    private int capacity;

    private int count;

    private static final int GROW_FACTOR = 2;

    public MyStack(){
        capacity = 8;
        storage = new int[capacity];
        count = 0;
    }
    public MyStack(int initialCapacity){
        if(initialCapacity < 1) throw new IllegalArgumentException("capacity too small");
        this.capacity = initialCapacity;
        storage = new int[initialCapacity];
        count = 0;
    }
    //扩容
    public void ensureCapacity(){
        int newCapacity = capacity * GROW_FACTOR;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
        System.out.println("扩容操作");
    }
    //入栈
    public void push(int value){
        if(count == capacity) ensureCapacity();//扩容
        storage[count++] = value;
    }
    //返回栈顶元素并出栈
    public int pop(){
        count--;
        if(-1 == count) throw new IllegalArgumentException("stack is empty");
        return storage[count];
    }
    //返回栈顶元素不出栈
    public int peek(){
        if(0 == count) throw new IllegalArgumentException("stack is empty");
        return storage[count - 1];
    }
    public boolean isEmpty(){
        return count == 0;
    }
    public int size(){
        return count;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.size());
    }

}































