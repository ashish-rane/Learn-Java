package com.xpert.java;

public class SharedResource {

    int counter;

    public  int getCounter() {
        return counter;
    }

    public  void incCounter() {
        this.counter++;
    }

    public SharedResource(){
        counter = 0;
    }
}
