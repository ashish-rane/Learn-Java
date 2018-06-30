package com.xpert.java.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Method references allow us to point to method by their name.
 * can be used to point to static methods, instance method and constructor/new operator
 * should use :: operator with method name
 */
public class MethodRefDemo {

    public static void main(String[] args){
        List<String>  l = Arrays.asList(new String("This is a test string").split(" "));

        l.forEach(str -> System.out.println(str)); // normal lambda

        // instead we can simply pass a method reference which accepts a string
        l.forEach(System.out::println);
    }
}
