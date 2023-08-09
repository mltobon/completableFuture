package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample4 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * SupplyAsync returns a value after finishing the process
     * ThenApply execute a method after finishing process using the return value (Function - transform)
     * about returning
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Calling longNetworkProcess");
        CompletableFuture.supplyAsync(()->longNetworkProcess(10))
                .thenApply(value ->doSomeOperations(value))
                .thenApply(CompletableFutureSample4::doOtherOperation)
                .thenAccept(value -> System.out.println("result: " + value));
        System.out.println("Process called...");
        System.out.println("Sleep main thread...");
        sleepThread(7000);
    }

    private static void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int doSomeOperations(int value){
        return value*1000;
    }

    public static int doOtherOperation(int value) {
       return value*2000;
    }
    public static int longNetworkProcess(int value) {
        sleepThread(5000);
        return value*10;
    }
}