package com.sample.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSample {

    public static void main(String[] args) {
        // Create an exectutor service
        // Make a runnable task - is void. You can pass runnable to a thread (not just
        // the executor service)
        // Make a callable task - can return a method. Can only be passed to the
        // executor service

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 4 second lag before running runnable
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(4000);
                System.out.println("Runnning runnable.... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            System.out.println("Calling callable.... ");
            TimeUnit.MILLISECONDS.sleep(100);
            return "Calling callable done";

        };

        executor.execute(runnableTask);
        Future<String> futureString = executor.submit(callableTask);

        try {
            // this will wait until all of the callable tasks are done
            String result = futureString.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Lets try to make callable tasks into an array

        List<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        try {

            List<Future<String>> futures = executor.invokeAll(callableTasks);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After usage, shutdown the executor service so that I won't accept processes
        // any more
        executor.shutdown();
        try {
            // terminates after a 5 second amount of time
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

    }
}
