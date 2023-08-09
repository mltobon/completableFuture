package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample3 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * RunAsync do not return anything, use it when you just need to execute a method in other thread but does not mind
     * about returning
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Calling longNetworkProcess without return value");
        CompletableFuture.runAsync(()->otherLongProcess("Successful"));
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

    public static void otherLongProcess(String value) {
        sleepThread(5000);
        System.out.println("Long process ended "+value);
    }
    public static int longNetworkProcess(int value) {
        sleepThread(5000);
        return value*10;
    }
}