package org.future.application;

public class SimpleThread2 {
    /**
     * Call long process to see how main thread wait until the long process is done
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println("Calling longNetworkProcess");
        int result = longNetworkProcess(10);
        System.out.println("result of process: "+result);
        System.out.println("Process ended successfully");
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