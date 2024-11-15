package com.workspace.multithreading;

public class Producer extends Thread{

    Company company;

    //constructor
    public Producer(Company company) {
      this.company = company;
    }

    public void run() {
        int i = 1;
        while(true){
           company.produceItem(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
