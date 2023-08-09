package org.future.application;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSample8 {
    /**
     * Call long process using CompletableFuture, sleep main thread to see the result
     * Then combine, wait two futures and apply an operation Bifunction
     * @param args
     */
    public static void main(String[] args) {
        int value = 5;
        getValueAsync(value).thenCombine(getAnotherValueAsync(value),(v1,v2) ->v1 + v2)
                .thenAccept(System.out::println);
    }

    public static CompletableFuture<Integer> getValueAsync(int value) {
        return CompletableFuture.supplyAsync(()->value*2);
    }

    public static CompletableFuture<Integer> getAnotherValueAsync(int value) {
        return CompletableFuture.supplyAsync(()->value*5);
    }
}