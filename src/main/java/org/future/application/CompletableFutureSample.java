package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample {
    /**
     * Call long process using CompletableFuture, it is executed in other thread
     * SupplyAsync returns a value after finishing the process
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println("Calling longNetworkProcess");
        CompletableFuture.supplyAsync(()->longNetworkProcess(10));
        System.out.println("Process called...");
    }

    public static int longNetworkProcess(int value) {
        try {
            System.out.println(Thread.currentThread());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value*10;
    }
}