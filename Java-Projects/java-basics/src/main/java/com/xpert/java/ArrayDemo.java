package com.xpert.java;

import java.util.Arrays;

public class ArrayDemo {

    public  static void main(String[] args){

        int[] array = {2,4,7,5,1,9};

        for(int i = 0; i < array.length; i++){
            System.out.println("Array " + Integer.toString(array[i]));
        }

        System.out.println("Binary Search " + Arrays.binarySearch(array, 7));
        Arrays.sort(array);

        Arrays.copyOfRange(array, 1, 4);

        // Multi-Dimensional Arrays

        int [][] a = new int[3][4];
        int [][] b = {
                        {3,4,5},
                        {3,2,},
                        {7,3,6},
                        {9,1,2,8}
                      };

        System.out.println("Length of row 1 " + b[0].length);
        System.out.println("Length of row 2 " + b[1].length);
        System.out.println("Length of row 1 " + b[2].length);
        System.out.println("Length of row 1 " + b[3].length);

        for (int i =0; i< b.length; i++){
            for(int j=0; j< b[i].length; j++){
                System.out.printf("b[%d][%d] = %d\n", i, j, b[i][j]);
            }
        }

        for(int[] innerArray : b){
            for(int elem : innerArray){
                System.out.print(elem + " ");
            }
            System.out.println();
        }

    }
}
