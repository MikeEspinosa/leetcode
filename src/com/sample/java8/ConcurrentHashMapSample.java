package com.sample.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapSample {

    public static void main(String[] args)  throws InterruptedException{
        
        // compute parallel100 using the hashmap object
        Map<String, Integer> map = new HashMap<>();              
        List<Integer> sumList = parallelSum100(map, 100);
        
        // for some reason when the amount of each some is not equals to 100. Something is wrong.....
        // this has to do with concurrency and usage of hashmaps
        assert 1 == sumList.stream().distinct().count();
        long wrongResultCount = sumList.stream().filter(num -> num != 100).count();
        System.out.println("Using Hashmap, wrong result count is " + wrongResultCount);
        assert (wrongResultCount > 0);
        
        
        // compute parallel100 using the concurrent hashmap object
        Map<String, Integer> concurrentHashmap = new ConcurrentHashMap<>();              
        List<Integer> concurrentSumList = parallelSum100(concurrentHashmap, 100);
        
        // Concurrent hashmap object is thread safe , this the results will always be correct which is 100
        assert 1 == concurrentSumList.stream().distinct().count();
        wrongResultCount = concurrentSumList.stream().filter(num -> num != 100).count();
        
        System.out.println("Using ConcurrentHashmap, wrong result count is " + wrongResultCount);
        assert (wrongResultCount == 0);
        
        
    }

    
    // even with parallel strings, you would expect it to compute each array to 100
    // but actually it depends on the map object you are giving them
    private static List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes)
            throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        // ComputeIfPresent is get(key) and put(key,value) in one go
                        map.computeIfPresent("test", (key, value) -> value + 1);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
        }
        return sumList;
    }

}
