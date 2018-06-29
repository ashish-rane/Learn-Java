package com.xpert.java.select;

import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientSocketSelect implements  Runnable{
    private String host;
    private int port;
    private int numThreads;
    private List<Thread> clientThreads;
    private List<SocketChannel> channels;
    private boolean stop;

    public ClientSocketSelect(String host, int port, int num_threads){
        this.host = host;
        this.port = port;
        this.numThreads = num_threads;
        clientThreads = new ArrayList<>(num_threads);
        //channels = new ArrayList<>(num_threads);
    }

    public void start(){
        for (int i = 0; i < numThreads; i++) {
            Thread t = new Thread(this, String.format("Client thread %d", i + 1));
            clientThreads.add(t);
            System.out.println("Starting Thread " + t.getName());
            t.start();
        }

        for (Thread t : clientThreads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        stop = true;
    }

    @Override
    public void run() {
        Random random = new Random();
        // Trick to generate random number between two numbers
        // say between 500 and 1000 ms
        int sleepTime = random.nextInt(500) + 500;
        try {
            Socket s = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            for (int i = 0; i < 10; i++) {
                String str = String.format("%s sent message %d",Thread.currentThread().getName(), i + 1 );
                writer.write(str);
                writer.newLine();
                writer.flush();

                Thread.sleep(sleepTime);

                String msg = reader.readLine();
                System.out.println("Server sent back: " + msg);
            }

            System.out.println(Thread.currentThread().getName() + " Closing");
            s.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
