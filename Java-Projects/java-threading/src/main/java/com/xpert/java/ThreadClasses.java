package com.xpert.java;

public class ThreadClasses {

    /**
     * Threading using the base Class Thread
     */
    public static void ThreadDemo() throws InterruptedException {

        Thread t = new  Thread("Extend thread"){

            @Override
            public void run() {
                int count = 10;
                while (count > 0){
                    System.out.println(getName() + " " + count);
                    System.out.println("Is Daemon " + isDaemon());
                    System.out.println("State " + getState());
                    System.out.println("Priority " + getPriority());
                    System.out.println("Thread Group "  + this.getThreadGroup().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count--;
                }
            }
        };

        t.start();
        t.join();
    }

    /**
     * Threading using Runnable interface
     *
     */
    public static void RunnableDemo() throws InterruptedException {

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int count =0;
                while(count < 10){
                    count++;
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Implement Runnable");
        t.start();
        t.join(); // makes the current thread wait until the thread t is finished
    }
}
