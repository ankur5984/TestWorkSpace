package com.workspace.multithreading;

public class Consumer extends Thread{

    Company company;

    //constructor
    public Consumer(Company company) {
      this.company = company;
    }

    public void run() {
        while(true){
            try {
                company.consumeItem();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
