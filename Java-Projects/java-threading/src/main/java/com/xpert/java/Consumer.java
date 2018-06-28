package com.xpert.java;

public class Consumer implements Runnable {

    private SharedResource resource;
    private Thread thread;
    private boolean stop;

    public Consumer(SharedResource resource){
        this.resource = resource;
    }

    public synchronized void start(){
        if(thread == null || !thread.isAlive()) {
            thread = new Thread(this, "Consumer");
            stop = false;
            thread.start();
        }
    }

    @Override
    public void run() {
         while(!stop){
             synchronized (resource) {

                 int counter = resource.getCounter();
                 System.out.println(Thread.currentThread().getName() + " " + counter);
                 resource.notify(); // Notify the producer
                 try {
                     resource.wait(); // Wait for the next counter
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 if (counter == 1000) {
                     stop = true;
                 }
             }
         }
    }
}
