package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
public class RetainBestCache<T>{
    Map<String, Integer> map;
    int size;
    DataSource<String, Integer> ds;
    PriorityQueue<Node> heap;

    public RetainBestCache(DataSource<String, Integer> ds, int entriesToRetain){
        this.ds = ds;
        int size = entriesToRetain;
        this.map = new HashMap<>();
        this.heap = new PriorityQueue<Node>((a, b) -> (a.t.getRank() - b.t.getRank()));
    }

    public Integer get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            T t = ds.get(key);
            if(map.size() < size){
                map.put(key, t);
                heap.offer(new Node(key, t));
            }else{
                if(t.getRank() > heap.peek().t.getRank()){
                    Node n = heap.poll();
                    map.remove(n.key);
                    map.put(key, t);
                    heap.offer(new Node(key, t));
                }
            }
            return t;
        }
    }
}

class Node{
    String key;
    int t;
    public Node(String k, int t){
        this.key = k;
        this.t = t;
    }
}

class DataSource<K, T>{
    Map<K, T> map;
    public DataSource(){
        map = new HashMap<>();
    }
}

*/


