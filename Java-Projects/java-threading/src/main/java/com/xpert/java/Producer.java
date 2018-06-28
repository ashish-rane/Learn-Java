package com.xpert.java;

import java.io.PrintWriter;

public class Producer implements Runnable{

    private SharedResource resource;
    private boolean stop;
    private Thread thread;

    public Producer(SharedResource resource){
        this.resource = resource;
    }

    public synchronized void start(){
        if(thread == null || !thread.isAlive()) {
            thread = new Thread(this, "Producer Thread");
            stop = false;
            thread.start();
        }
    }

    @Override
    public void run() {
        while(!stop) {
            synchronized (resource) {
                resource.incCounter(); // Produce the next
                int counter = resource.getCounter();
                System.out.println("Producer " + counter);
                resource.notify(); // Notify the receivers
                try {
                    resource.wait(); // Wait for receiver to consume
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
