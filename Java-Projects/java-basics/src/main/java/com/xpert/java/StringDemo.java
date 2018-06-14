package com.xpert.java;

public class StringDemo {

    public static  void main(String[] args){

        String testStr = "This is a test string";

        System.out.println("Length of string " + testStr.length());

        // Format
        String formatStr = String.format("This is a %s %d %f %b string", "formatted", 42, 5.6f, true);
        System.out.println(formatStr);

        System.out.println(String.format("s1.compareToIgnoreCase(s2) => %b", testStr.compareToIgnoreCase(formatStr)));
        System.out.println(String.format("s1.startswith(%s) => %b", "This", testStr.startsWith("This")));
        System.out.println(String.format("s1.endswith(%s) => %b", "This", testStr.endsWith("This")));

        System.out.println(String.format("Element 7 by index : %c", testStr.charAt(7)));

        System.out.println(String.format("Find index of test : %d", testStr.indexOf("test")));

        for (String token: testStr.split(" ")){
            System.out.println("Tokens: " + token);
        }

        System.out.println("Substring(1,9) : " + testStr.substring(1,9) );
        System.out.println("Lower Case: " + testStr.toLowerCase());
        System.out.println("Upper Case: " + testStr.toUpperCase());


        // StringBuilder is not synchronized
        // StringBuffer is synchronized but essentially same as StringBuilder
        // we can use StringBuilder within a synchronized block to get same behavior
        StringBuilder sb = new StringBuilder();
        sb.append("First,");
        sb.insert(0, "Second");
        System.out.println("Length " + sb.length());
        sb.delete(1,3); // in-place
        sb.insert(4, "Third");
        System.out.println("Index of First " + sb.indexOf("First"));
        System.out.println("Substring " + sb.substring(4,8));
        System.out.println("ToString " + sb.toString());
        sb.reverse();// in place
        System.out.println("Reversed string " + sb.toString());
        sb.replace(5, 7, "Fourth"); // in-place
        System.out.println("Replaced string " + sb.toString());
    }
}
