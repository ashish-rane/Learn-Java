package com.xpert.java;

import java.io.*;

/**
 * Stream classes are used for binary streams IO
 */
public class StreamsDemo {

    /**
     * contains internal buffer that contains bytes can be read or written
     */
    public static void ByteArrayStreamDemo(){

        byte[] arr = null;
        try(ByteArrayOutputStream os = new ByteArrayOutputStream()){
            os.write(0x01);
            byte[] arr1 = {0x0A, 0x0B, 0x0C};
            os.write(arr1);

            System.out.println("Size " + os.size());

            System.out.println(os);

            arr = os.toByteArray();


            try(ByteArrayInputStream is =  new ByteArrayInputStream(arr)){
                int bytesCount = is.available();
                int b =0;
                while((b = is.read()) != -1){
                    System.out.printf("%02x ", b);
                }
                System.out.println();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * lets read and write primitive Java Data types from underlying
     * stream in machine-independent way.
     * This stream is thus a wrapper around another stream and provides additional functionality.
     */
    public static void DataStreamDemo(){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            dos.write(0x65);
            dos.writeInt(100);
            dos.writeLong(500);
            dos.writeBoolean(true);
            dos.writeChar('A');
            dos.writeBytes("XYZ");
            dos.writeFloat(2.3f);
            dos.writeUTF("UTFString");

            byte[] arr = baos.toByteArray();
            for(byte b: arr){
                System.out.printf("0x%02x ", b);
            }
            System.out.println();

            ByteArrayInputStream bais =  new ByteArrayInputStream(arr);
            DataInputStream dis = new DataInputStream(bais);
            System.out.println(dis.readByte());
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readChar());
            byte[] rb = new byte[3];
            dis.read(rb);
            System.out.println(rb.toString());
            System.out.println(dis.readFloat());
            System.out.println(dis.readUTF());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
        }
    }

    /**
     * Used for Binary File IO
     */
    public static void FileStreamDemo(){

        try(FileOutputStream fos = new FileOutputStream("TestFile.bin")){
            fos.write(1);
            fos.write(new byte[]{0x33, 0x45,0x65, 0x65});
            fos.flush(); // writes the buffer to the file
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try(FileInputStream fis = new FileInputStream("TestFile.bin")){
            // returns number of bytes available for reading
            int availablebytes = fis.available();
            byte[] buffer = new byte[availablebytes];
            fis.read(buffer, 0, availablebytes);

            for(byte b: buffer){
                System.out.print(String.format("0x%02X ", b));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Used to connect an input stream to output stream.
     * Any binary data pushed to the outputstream is available to be
     * read from the input stream.
     * It provides a internal buffer to store data thus decoupling the streams.
     */
    public static void PipedStreamDemo(){
        byte[] bArr = new byte[]{0x10, 0x20, 0x30, 0x45, 0x65,0x75, 0x40 };

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pos.connect(pis);

            // write partially
            pos.write(bArr, 0, 3);

            System.out.println("Available bytes " + pis.available());

            // flush
            pos.flush();

            int availableBytes = pis.available();
            while (availableBytes > 0){
                System.out.print(String.format("0x%02X ", pis.read()));
                availableBytes--;
            }
            pis.close();
            pos.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {

        }
    }

    /**
     * Buffered streams add a buffering functionality to
     * byte streams so instead of reading one byte at a time
     * this will read a block of bytes thereby improving IO performances.
     */
    public static void BufferedStreamsDemo(){

        byte[] buffer = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(BufferedOutputStream bos = new BufferedOutputStream(baos)){
            bos.write(new byte[]{0x10,0x20,0x30,0x40,0x50,0x60,0x70});
            // for buffered streams flush is important to ensure
            // the interal buffer is completely written to the underlying stream.
            bos.flush();
            buffer = baos.toByteArray();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try(BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(buffer))){
            while(bis.available() > 0){
                System.out.print(String.format("0x%02X ", bis.read()));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * PrintStream adds functionality in the underlying stream
     * ability to print representations of various datatypes correctly.
     * Basically adds formatting functionality.
     */
    public static void PrintStreamDemo(){
        PrintStream ps = new PrintStream(System.out);

        ps.printf("This is a formatted string %b %d %.2f", true, 1, 12.354);
    }

}
