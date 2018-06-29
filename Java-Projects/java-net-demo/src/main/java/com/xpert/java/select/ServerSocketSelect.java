package com.xpert.java.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * The new I/O or nio package allows processing new connections and
 * monitoring incoming data from existing connections in a single thread.
 * It is based on Channels and Buffers. This allows us to read data in bulk
 * only limited by size of buffer allocated.
 * The NIO system is based on the low level OS socket select() feature.
 * In this case instead of waiting in a seperate thread montioring for incoming data
 * from an individual connection we monitoring a select set of connections and
 * get notify which ones have data available in the buffers. This way
 * when we actually call a method like accept() or read() they don't block.
 * This is because the Selector has already indicated which ones have I/O activity on them.
 */
public class ServerSocketSelect implements Runnable {

    // server listening port for connections
    private int port;
    private boolean stop;
    private Thread serverThread;
    private Selector selector;
    private ServerSocketChannel ssc;

    public ServerSocketSelect(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        if (serverThread == null) {
            serverThread = new Thread(this, "server thread");

            // Create a Server Socket Channel from nio package
            ssc = ServerSocketChannel.open();

            // Set this server socket channel to non-blocking so that we
            // can use select
            ssc.configureBlocking(false);

            // Get the socket connected to this channel and
            // bind it to the port on which we are listening for connections
            ServerSocket serverSocket = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocket.bind(address);

            // Create a new Selector
            selector = Selector.open();

            // At the beginning we have just the server socket as
            // part of our selector set.
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server Listening on port " + port);

            // Start our selector loop logic in the thread
            serverThread.start();
        }
    }

    public void stop(){
        if(serverThread != null && serverThread.isAlive()){
            stop = true;
        }
    }

    private void acceptConnection() throws IOException {
        System.out.println("Accepting incoming connection");

        // this would not block
        Socket s = ssc.socket().accept();
        System.out.println("Got connection from " + s);

        // Make the channel for this connected socket non-blocking
        // so that we can use it with select
        SocketChannel sc = s.getChannel();
        sc.configureBlocking(false);

        // add to selector
        sc.register(selector, SelectionKey.OP_READ);
    }

    private boolean processingIncomingData(SocketChannel sc) throws IOException {

        // Allocate a buffer to read the incoming data into.
        ByteBuffer inBuffer = ByteBuffer.allocate(4096);

        sc.read(inBuffer);

        // The flip() method changes the mode to reading mode.
        // By  default the buffer is in write mode. if we call this twice we again will be in writing mode.
        inBuffer.flip();

        // if no data that means the remote socket is closed.
        if(inBuffer.limit() == 0){
            return true;
        }

        // Simple logic converts the incoming string to uppercase and writes that
        // back to the socket.
        // In reality this would be a complex business logic.
        String s = new String(inBuffer.array(), Charset.forName("UTF-8"));
        System.out.println(s);
        s = s.toUpperCase();
        ByteBuffer outBuffer = ByteBuffer.wrap(s.getBytes(Charset.forName("UTF-8")));

        sc.write(outBuffer);

        return false;
    }

    @Override
    public void run() {
        try {
            while (!stop) {

                // Check if have any I/O activity on any members of the select group
                int num = selector.select(); // returns number of sockets with I/O activity

                // We have been told to stop
                if(stop){
                    return;
                }

                if (num == 0) {
                    // none of the sockets have any I/O
                    continue;
                }

                // If we have some I/O get the keys corresponding
                // to the them and process one by one
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();

                    // Check  the flags to see what kind of activity
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
                            SelectionKey.OP_ACCEPT) {

                        // It is an incoming connection
                        acceptConnection();
                    } else if ((key.readyOps() & SelectionKey.OP_READ) ==
                            SelectionKey.OP_READ) {
                        // It is incoming data on existing connection
                        SocketChannel sc = null;
                        boolean closeConnection = false;
                        try {
                            sc = (SocketChannel) key.channel();
                            closeConnection = processingIncomingData(sc);
                        } catch (IOException e) {
                            // on exception, remove this socket from selector
                            closeConnection = true;
                        } finally {
                            if (closeConnection) {
                                // If the connection is dead, remove this
                                // socket from selector
                                key.cancel();

                                sc.socket().close();
                            }
                        }
                    }
                }
                keys.clear(); // clear the keys which we processed
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
