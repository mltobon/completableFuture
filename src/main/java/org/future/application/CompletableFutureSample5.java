package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample5 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * SupplyAsync returns a value after finishing the process
     * ThenApply execute a method after finishing process using the return value (Function - transform)
     * ThenRun execute a method after finishing process (Runnable - void)
     * about returning
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Calling longNetworkProcess");
        CompletableFuture.supplyAsync(()->longNetworkProcess(10))
                .thenApply(value ->doSomeOperations(value))
                .thenApply(CompletableFutureSample5::doOtherOperation)
                .thenAccept(value -> System.out.println("result: " + value))
                .thenRun(()->otherLongProcess())
                .thenRun(()->otherLongProcess2());
        System.out.println("Process called...");
        System.out.println("Sleep main thread...");
        sleepThread(10000);
        System.out.println("Awake main thread...");
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
        sleepThread(2000);
        return value*10;
    }

    public static void otherLongProcess() {
        sleepThread(3000);
        System.out.println("Finished the long process");
    }
    public static void otherLongProcess2() {
        sleepThread(3000);
        System.out.println("Finished the long process 2");
    }
}