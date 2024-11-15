package com.workspace.multithreading;

public class Company {

    int n;
    boolean flag = false;

    synchronized public void produceItem(int n) {
        if(flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        System.out.println("Producer Produces song : "+ this.n);
        flag = true;
        notify();
    }

    synchronized public int consumeItem() throws InterruptedException {
        if(!flag) {
            wait();
        }
        System.out.println("Consumer consumes song : "+ this.n);
        flag = false;
        notify();
        return this.n;
    }
}
