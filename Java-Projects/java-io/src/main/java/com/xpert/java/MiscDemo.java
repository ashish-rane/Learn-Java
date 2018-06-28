package com.xpert.java;

import java.io.*;

public class MiscDemo {

    /**
     * File class is used to do file operations like check of existence,
     * get path etc...
     */
    public static void FileDemo() throws IOException {
        File file = new File("Test.txt");
        boolean exists = file.exists();
        System.out.println("Exists " + exists);
        boolean created = file.createNewFile();
        System.out.println("Created " + created);

        System.out.println("Absolute Path " + file.getAbsolutePath());
        System.out.println("Parent " + file.getParent());
        file.setReadOnly();
        file.delete();

        // We can also use File class for a directory
        final String current_dir = System.getProperty("user.dir");
        File file2 = new File(current_dir);

        System.out.println("Absolute Dir Path " + file2.getAbsolutePath());
        System.out.println("Parent " +  file2.getParent());

        String[] files = file2.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        for(String f: files){
            System.out.println(f);
        }

        File[] files2 = file2.listFiles();
        for(File f : files2){
            System.out.println(f.getName() + " " + f.isDirectory());
        }
    }

    public static void ConsoleDemo(){
        Console console = null;
        console = System.console(); // does not work return null

        if(console != null){
            String s = console.readLine("Name: ");

            System.out.println("Hello " + s);
        }
    }

    /**
     * the bridging classes are InputStreamReader and OutputStream writer.
     * They allow us to read a byte stream as a character stream OR write to
     * character stream which internally writes to an underlying byte stream.
     */
    public static void BridgingClassesDemo() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your name : ");
        String s = reader.readLine();
        System.out.println("Hello " + s);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos);
        try(BufferedWriter bw = new BufferedWriter(osw)){

            bw.write("This is written to character writer");
            bw.flush(); // IMP: for buffered writers

            System.out.println("Byte Stream buffer ");
            byte[] bArr = baos.toByteArray();
            for (byte b : bArr){
                System.out.printf("%c", b);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
