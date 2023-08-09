package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample2 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * SupplyAsync returns a value after finishing the process
     * ThenAccept uses the return of process to do something (Consumer - Void)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Calling longNetworkProcess");
        CompletableFuture.supplyAsync(()->longNetworkProcess(10))
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

    public static int longNetworkProcess(int value) {
        sleepThread(5000);
        return value*10;
    }
}