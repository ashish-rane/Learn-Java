package com.xpert.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Functional interfaces have a single functionality to exhibit.
 * ex: Comparable interface with a single "compareTo" for comparison purpose.
 * Predicate<T> is a functional interface which has test(obj) method that returns a boolean
 */
public class FunctionalInterfaceDemo {

    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for(Integer n: list){
            if(predicate.test(n)){
                System.out.println(n + " ");
            }
        }
    }

    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        System.out.println("Predicate<T> examples");
        eval(list, n ->true); // always return true irrespective of contents

        System.out.println("Print even numbers");
        eval(list, n -> n%2 == 0); // return where modulus is 0

        System.out.println("Print odd numbers");
        eval(list, n -> n%2 != 0);


        // Use the Function<T,R> where T is parameter and R is return type
        System.out.println("Function<T,R> interface composing");
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        // compose two functional interface into one unit which gets executed sequentially
        Function<Integer, String> quoteIntToString = quote.compose(intToString);

        System.out.println(quoteIntToString.apply(512)); // should print '512'

        // Our custom functional interface
        System.out.println("Custom Functional interface");
        List<String> strList = Arrays.asList( new String("This is a test string").split(" "));
        Transform<String> upperCase = (s) -> s.toUpperCase();
        Transform<String> lowerCase = (s) -> s.toLowerCase();

        for(String s : strList){
            System.out.println(upperCase.map(s));
            System.out.println(lowerCase.map(s));
        }

        // using the stream API "fluent" style
        System.out.println("Stream Fluent API");
        strList.stream().map(String::toUpperCase).forEach(System.out::println);
        strList.stream().map(String::toLowerCase).forEach(System.out::println);
    }
}

/**
 * Our Custom functional interface which does some transformation on the type and return
 * modified type.
 * @param <T>
 */
@FunctionalInterface
interface Transform<T>{
    T map(T t);
}