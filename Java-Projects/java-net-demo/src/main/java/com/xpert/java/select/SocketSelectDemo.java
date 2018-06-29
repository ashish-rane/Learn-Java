package com.xpert.java.select;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocketSelectDemo {

    public static void main(String[] args) throws IOException {

        String mode = args[0];
        System.out.println("Running in Mode... " + mode);
        if(mode.equals("client")){
            String host = args[1];
            int port = Integer.parseInt(args[2]);
            ClientSocketSelect client = new ClientSocketSelect(host, port, 3);
            client.start();
        }
        else if (mode.equals("server")){
            int port = Integer.parseInt(args[1]);
            ServerSocketSelect server = new ServerSocketSelect(port);
            server.start();

            System.out.println("Press Enter to stop server...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();

            server.stop();
        }
        else {
            System.out.println("Wrong arguments");
        }
    }

    /**
     * This can be used for unit testing using server and client.
     * @return
     */
    public static Process start() throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classPath = System.getProperty("java.class.path");
        String className = ServerSocketSelect.class.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classPath, className);
        return builder.start();
    }
}
