package com.xpert.java;

import java.io.*;

/**
 * Reader and Writer classes are used for Character Streams IO
 */
public class ReaderWriterDemo {

    /**
     * StringReader is used to read from a string
     * StringWriter is used to write to a string (usefull when contructing command string
     * from different fields to be sent down a socket/port
     */
    public static void StringRWDemo() throws IOException {
        StringReader reader = new StringReader("This is a test string.");

        reader.mark(5);

        // Read single character
        System.out.println(reader.read());

        // Read n number of characters
        char arr[] = new char[255];
        int read_len = reader.read(arr, 1, 10);
        System.out.println("Read Length " + read_len);
        System.out.print("Characters read ");
        for(char c : arr){
            System.out.print( c + " ");
        }

        StringWriter writer = new StringWriter(100);
        writer.append('A');
        writer.write(1);
        writer.write("Hello World");

        System.out.println("\n Resulting string " + writer.toString()) ;
    }

    /**
     * Piped Reader and Writer can be connected so any characters
     * written on the writer will be available for reading at the connected reader
     */
    public static void PipedRWDemo() throws IOException {

        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        reader.connect(writer);

        writer.write("This is a connected writer");

        StringBuilder sb = new StringBuilder();
        while(reader.ready()){
            char[] charBuff = new char[100];
            reader.read(charBuff,0, 100);
            sb.append(charBuff);
        }

        System.out.println(sb.toString());

        reader.close();
        writer.close();
    }

    /**
     * File Reader and Writer class allow File IO for character streams
     */
    public static void FileRWDemo() {

        try(FileWriter writer = new FileWriter("TestFile.txt")){
            writer.write("This is written using FileWriter");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try(FileReader reader = new FileReader("TestFile.txt")){
            StringBuilder sb = new StringBuilder();
            while (reader.ready()){
                char[] cbuff = new char[100];
                reader.read(cbuff,0, 100);
                sb.append(cbuff);
            }

            System.out.println(sb.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Buffered Reader and Writer classes add buffering capability to
     * the underlying reader and writer thereby improving performance
     * by reading a chunk of data at a time instead of single character
     */
    public static void BufferedRWDemo() throws IOException {

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);

        bw.write("This is written using a Buffered Writer ");
        bw.flush();

        String s = sw.toString();

        BufferedReader reader = new BufferedReader(new StringReader(s));
        if (reader.ready()){
            System.out.print(reader.readLine());
        }

    }

    /**
     * Adds formatting functionality to underlying  writer.
     */
    public static void PrintWriterDemo(){
        PrintWriter pw = new PrintWriter(System.out);

        pw.printf("This is a formatted string %b %02X %.2f", true, 0x65, 5.234);

        // This flush() is required since PrintWriter uses a buffer by default.
        pw.flush();
    }
}
