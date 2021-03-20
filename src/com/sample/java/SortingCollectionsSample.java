package com.sample.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class SortingCollectionsSample {

    public static void main(String[] args) {

        // PriorityQueue descending order
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(6);
        priorityQueue.add(4);
        priorityQueue.add(2);

        System.out.println("Priority Queue result : ");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

        // Arraylist collection sort
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        arrayList.add(5);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(6);
        arrayList.add(4);
        arrayList.add(2);

        Collections.sort(arrayList, Collections.reverseOrder());
        
        System.out.println("ArrayList result : ");
        arrayList.stream().forEach(System.out::println);

        // Tree hashmap

    }
}
