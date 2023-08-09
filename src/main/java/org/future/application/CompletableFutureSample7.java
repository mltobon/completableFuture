package org.future.application;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureSample7 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * SupplyAsync returns a value after finishing the process
     * ThenAccept uses the return of process to do something (Consumer - Void)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Calling longNetworkProcess");
        CompletableFuture.supplyAsync(()->longNetworkProcess(10))
                .completeOnTimeout(99,1, TimeUnit.SECONDS)
                .orTimeout(1,TimeUnit.SECONDS)
                .thenAccept(System.out::println);
              //  .join();
        System.out.println("Process called...");
        System.out.println("Sleep main thread...");
        sleepThread(2000);
        System.out.println("awake main thread...");

    }

    private static void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int longNetworkProcess(int value) {
        System.out.println("Long network process started...");
        sleepThread(5000);
        System.out.println("Long network process ended...");
        return value*10;
    }
}