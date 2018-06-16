package com.xpert.java;

public class GenericsDemo {

    /**
     * T is an generic tyoe which unbounded can be any class
     *
     */
    public static <T> void printArray(T[] array){
        for(T elem : array){
            System.out.print(elem.toString() + " ");
        }
        System.out.println();
    }

    /**
     * T is generic but now a bounded type generic parameter
     * which means now the restriction for any class replacing T
     * during instantiation has to be a subclass of Comparable.
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z){
        T max = x;

        // Below we can call compare to since we know T extends Comparable
        // whihc means it has the method.
        if(y.compareTo(max) > 0){
            max = y;
        }

        if(z.compareTo(max) > 0){
            max = z;
        }

        return max;
    }

    public static void main(String[] args){

        // Generic Type.
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };

        printArray(intArray);
        printArray(doubleArray);

        // Bounded Type Generic
        System.out.printf("Max of %d, %d and %d is %d\n\n",
                3, 4, 5, maximum( 3, 4, 5 ));

        System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));

        System.out.printf("Max of %s, %s and %s is %s\n","pear",
                "apple", "orange", maximum("pear", "apple", "orange"));
    }
}
