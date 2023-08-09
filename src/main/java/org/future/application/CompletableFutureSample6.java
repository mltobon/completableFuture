package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample6 {
    /**
     * Call long process using CompletableFuture, it is executed in other thread
     * Managing exception: first use join to see the exception
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        CompletableFuture.supplyAsync(()->processValue(10))
                .exceptionally(error->100)
                .thenApply(CompletableFutureSample6::anotherProcessValue)
                .exceptionally(error->500)
                .thenAccept(System.out::println);
         // .join();
    }

    public static int processValue(int value) {
        System.out.println(Thread.currentThread());
        //launchException();
        return value*10;
    }

    public static int anotherProcessValue(int value) {
        System.out.println(Thread.currentThread());
        //launchException();
        return value*20;
    }

    public static void launchException(){
        throw new RuntimeException("Exception");
    }
}