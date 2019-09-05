package com.example.demo.collectionbasic;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author chenhe
 * @Date 2019/9/2 17:39
 * @Description
 */
public class HashMapDemo<K,V>  {

    Nodes<K,V> [] table;

    int size;

    int capacity ;

    int DEFAULT_INITIAL_CAPACITY = 1<<4;

    int MAX_CAPACITY = 1 << 30;

    public HashMapDemo(){
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    public HashMapDemo(int capacity){
        if(capacity < 0)  throw new IllegalArgumentException("");
        if(capacity > MAX_CAPACITY)  capacity = MAX_CAPACITY;
        this.capacity = capacity;
    }

    public V put(K key, V value){
        return  putValue(key, value);
    }

    private V putValue(K key, V value){
        Nodes<K,V> e, n, p = null;
        Nodes<K,V>[] t;
        int len;

        int hash = hasCode(key);
        if((t = table) == null || (len = table.length) == 0)
            len = (t = table = new Nodes[capacity]).length;

        if((e = t[hash&(len -1)]) == null){
            t[hash&(len -1)] = new Nodes<K, V>(key, value, null);
            size ++;
            return value;

        }else {
            n = e;
            do {
                if(hasCode(n.key) == hash &&(n.key == key || (key != null &&key.equals(n.key)))){
                    p = n;
                    break;
                }else if(null ==(n = e.next)) {
                    e.next = new Nodes<K, V>(key, value, null);
                    size ++;
                    break;
                }
            }while (n != null);
        }

        if(null != p){
            p.value = value;
        }
        return value;
    }

    public V get(K key){
        int hash = hasCode(key);
        int len = table.length;
        Nodes<K, V> first, n;
        if(null == (first = table[hash & (len - 1)])){
            return null;
        }else{
            do {
                if(hasCode(first.key) == hash && (key == first.key || key.equals(first.key))){
                    return first.value;
                }
            }while ((first = first.next) != null);
        }

        return null;
    }

    class Nodes<K,V> implements Map.Entry<K,V>{
        K key;
        V value;
        Nodes<K, V> next;

        public Nodes(K key, V value, Nodes<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    public int hasCode(K key){
        return key.hashCode();
    }

    public static void main(String[] args) {
        HashMapDemo<String, String> demo = new HashMapDemo<String, String>();
        demo.put("key1", "value1");
        demo.put("key2", "value2");
        demo.put("key3", "value3");
        System.out.println(demo.get("key1"));
        System.out.println(demo.get("key2"));
        System.out.println(demo.get("key3"));
        System.out.println(demo.size);
        System.out.println(demo.capacity);
    }
}
