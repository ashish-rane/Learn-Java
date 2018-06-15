package com.xpert.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List interface inherits from base collection interface.
 * AbstractList extends AbstractCollection and implements List
 * AbstractSequentialList (allows sequential access rather than random) extends AbstractList
 */
public class ListDemo {

    /**
     * ArrayList extends Abstract List to provide concrete implementation.
     * Provides dynamic array functionality with Random access
     */
    public static void ArrayListDemo(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(0, 3);
        arrayList.add(1);
        arrayList.add(7);
        arrayList.add(5);
        arrayList.add(8);
        arrayList.add(6);

        System.out.println("Contains 7 " + arrayList.contains(7));
        arrayList.forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("To String " + arrayList.toString());

        System.out.println(" At  Index 4 " + arrayList.get(4));
        System.out.println("Index of 7 " +  arrayList.indexOf(7));

        System.out.println("Is Empty " + arrayList.isEmpty());
        arrayList.remove(0);
        System.out.println("After removing element at Index 0 " + arrayList.toString());
        arrayList.remove((Integer)7);
        System.out.println("After removing element 7 " + arrayList.toString());

        System.out.println("Size " + arrayList.size());
        arrayList.set(0, 10);
        System.out.println("After setting element at 0 to 10" + arrayList.toString());

        arrayList.sort(((o1, o2) -> {
            if(o1 > o2) return 1;
            if(o1 < o2) return  -1;
            return 0;
        }));

        System.out.println("Sorted arrayList " + arrayList.toString());

        System.out.println("SubList 0 and 2 " + arrayList.subList(0,2));

        // Linq functionality using stream->filter->collect
        System.out.println("Even numbers " +
                arrayList.stream().filter(e -> e%2 == 0).collect(Collectors.toList()));

        List<Integer> list = Arrays.asList(3,2,1,4,5,7,6,9);

        arrayList.clear();

        System.out.println("Empty? " + arrayList.isEmpty());

    }

    /**
     * LinkedList extends AbstractSequentialList to provide concrete implementation.
     * Provides linked list sequential access
     */
    public static void LinkedListDemo(){
        LinkedList<String> ll =  new LinkedList<>();
        ll.add("F");
        ll.add("B");
        ll.add("D");
        ll.add("E");
        ll.add("C");
        ll.addLast("Z");
        ll.addFirst("A");
        ll.add(1, "A2");
        System.out.println("Original contents of ll: " + ll);

        ll.remove(4);
        System.out.println("removed at index 4: " + ll);
        ll.remove("F");
        System.out.println("removed element F: " + ll);

        ll.removeFirst();
        ll.removeLast();

        ll.addFirst("X");
        ll.addLast("P");
        System.out.println("First and Last Elements changed: " + ll);

        System.out.println("contains P " + ll.contains("P"));
        System.out.println("Get Element at index 5" + ll.get(5));
        System.out.println("Size " + ll.size());
        System.out.println("Is Empty " + ll.isEmpty());
        System.out.println("Peek First " + ll.peekFirst());
        System.out.println("Peek Last " + ll.peekLast());
        System.out.println("Get First " + ll.getFirst());
        System.out.println("Get Last " + ll.getLast());

        ll.set(4, "O");
        System.out.println("Set element at index 4 to O " + ll);

        System.out.println("SubList 1-4" + ll.subList(1,4));

        System.out.println("Postfixed with - using parallel stream" +
                ll.parallelStream().map(s-> s + "-").collect(Collectors.toList()));

        ll.sort((s1,s2)-> {
            if (s1.length() < s2.length()) return -1;
            if(s1.length()> s2.length()) return 1;
            return 0;
        });

        System.out.println("Sorted List " + ll);

        ll.clear();
        System.out.println("Is empty after clear " + ll.isEmpty());
    }

    public static void main(String[] args){
        System.out.println("------- ArrayList DEMO -----------");
        ArrayListDemo();

        System.out.println("------- LinkedList DEMO -----------");
        LinkedListDemo();
    }
}
