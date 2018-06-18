package com.xpert.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Hello world!
 *
 */
public class IODemo
{
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

    public static void main( String[] args )
    {
        ByteArrayStreamDemo();

        DataStreamDemo();
    }
}
