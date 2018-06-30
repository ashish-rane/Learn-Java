package com.xpert.java.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class LambdaDemo
{
    /**
     * functional programming and replace anonymous methods
     * Allows methods to passed as parameters thereby ability to callbacks or customize function execution
     */
    public static void LambdaDemo(){

    }

    interface MathOperation{
        int operation (int a, int b);
    }

    interface  GreetingService{
        int sayMessage(String message);
    }

    /**
     * The following method allows us to pass different concrete implementations of the
     * interface MathOperation. However instead of create seperate classes or
     * using anonymous classes that implement this interface we can pass lambda expressions
     * The method simply calls the method on the interface
     */
    private static int operate(int a, int b, MathOperation op){
        return op.operation(a,b);
    }

    public static void main( String[] args ) {

        // TODO: Lambda expressions, ? operator, Method References,

        // with type declaration
        MathOperation add = (int a, int b) -> a + b;

        // without type declaration
        MathOperation subtract = ( a, b) -> a - b;

        // with return statement and curly basis
        MathOperation mult = (a,b) -> {return a * b;};

        // now call the operate method with different lambdas
        System.out.println("Addition : " + operate(10,4, add));
        System.out.println("Subtraction : " + operate(10,4, subtract));
        System.out.println("Multiplication : " + operate(10,4, mult));

        // Lambda are also used in collections or algorithm where you can customize the logic
        List<String> l = new ArrayList<>(); // This is also java 8 feature where you use <> operator without specify the type
        String s = "This is a test string";
        l = Arrays.asList(s.split(" "));
        l.forEach(str -> System.out.println(str));


        l.sort((s1,s2) ->{
            if (s1.length() > s2.length()) return 1;
            if (s1.length() < s2.length()) return  -1;
            return 0;

        });

        l.forEach(str -> System.out.println(str));
    }
}
