package com.xpert.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPSocketDemo {

    public static boolean stop = false;
    static ServerSocket server = null;
    static List<Socket> clients = null;

    public static void main(String[] args) throws IOException {

        server = new ServerSocket(9001);
        clients = new ArrayList<>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!stop){
                    try {
                        System.out.println("Server Starting ...");
                        Socket client = server.accept();
                        if(stop){
                            client.close();
                            return;
                        }
                        BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));

                        System.out.println("Client sent : " + bf.readLine());
                        System.out.println("Server side socket Local Port " + client.getLocalPort());
                        System.out.println("Server side socket remote port " + client.getPort());
                        System.out.println("Server side socket local address " + client.getLocalAddress().toString());
                        System.out.println("Server side socket remote address " + client.getRemoteSocketAddress().toString());

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        writer.write("Hello from Server !!");
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        stop = true;
                    }
                }
                System.out.println("Server exiting ....");
            }
        }, "Server Accept Thread");

        t.start();

        Socket socket = new Socket("localhost", 9001);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("Hello Server");
        writer.newLine();
        writer.flush();

        System.out.println("Server sent: " + reader.readLine());
        System.out.println("Client side socket Local Port " + socket.getLocalPort());
        System.out.println("Client side socket Remote port " + socket.getPort());
        System.out.println("Client side socket Local Address " + socket.getLocalAddress().toString());
        System.out.println("Client side socket Remote Address " + socket.getRemoteSocketAddress().toString());

        socket.close();

        stop = true;
        // Connect a dummy socket to release the blocking accept call.
        // The thread will check for the stop flag and return.
        // Then we can safely call the server.close below.
        socket = new Socket("localhost", 9001);
        socket.close();
        server.close();
    }
}
