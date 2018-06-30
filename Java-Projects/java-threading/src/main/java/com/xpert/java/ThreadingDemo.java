package com.xpert.java;

/**
 * Hello world!
 *
 */
public class ThreadingDemo
{
    public static void SyncDemo(){
        SharedResource resource = new SharedResource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        producer.start();
        consumer.start();
    }

    public static void main( String[] args ) throws InterruptedException {

        //ThreadClasses.RunnableDemo();

        //ThreadClasses.ThreadDemo();

        SyncDemo();
    }
}
