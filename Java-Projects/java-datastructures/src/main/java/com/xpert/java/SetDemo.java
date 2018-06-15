package com.xpert.java;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * AbstractSet extends AbstractCollection and implements Set Interface
 * elements in all set collections  are unique.
 */
public class SetDemo {

    /**
     * HashSet stores information using hashing.
     * the hashcode of the data  is used as  index to  the data (Value)
     *
     */
    public static void HashSetDemo(){
        HashSet<String> hs = new HashSet<>();
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");

        System.out.println("Contains F "  + hs.contains("F"));
        System.out.println("Size " + hs.size());
        System.out.println("to string " + hs);
        hs.remove("E");
        System.out.println("After removal of E " + hs);
        hs.clear();
        System.out.println("Is Empty " + hs.isEmpty());

    }

    /**
     * LinkedHashSet maintains the elements in a Linked List
     * and maintains the insertion order
     * The API is same as HashSet as LinkedHashSet actually extends HashSet
     */
    public static void LinkedHashSetDemo(){
        LinkedHashSet<String> hs = new LinkedHashSet<>();
        // add elements to the hash set
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");
        System.out.println(hs);
    }

    /**
     * Uses a tree structure for storing data. Implements
     * SortedSet interface.
     * By default it sorts the elements in ascending order.
     * Access and retrieval is fast due to tree structure.
     */
    public static void TreeSetDemo(){
        TreeSet<String> ts = new TreeSet<>();
        // Add elements to the tree set
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");
        System.out.println(ts);

        System.out.println("Size " + ts.size());
        System.out.println("First " + ts.first());
        System.out.println("Last " + ts.last());
        System.out.println("Ceiling " + ts.ceiling("D"));
        System.out.println("Floor " + ts.floor("D"));
        System.out.println("Higher " + ts.higher("D"));
        System.out.println("Lower " + ts.lower("D"));
        System.out.println("Contains E" + ts.contains("E"));
        System.out.println("HeadSet to B" + ts.headSet("B"));
        System.out.println("TailSet from B" + ts.tailSet("B"));
        System.out.println("SubSet  " + ts.subSet("A", "F"));
        ts.clear();
        System.out.println("Is Empty " + ts.isEmpty());
    }



    public static void main(String[] args){

        System.out.println("--------- HashSet Demo ---------");
        HashSetDemo();

        System.out.println("--------- LinkedHashSet Demo ---------");
        LinkedHashSetDemo();

        System.out.println("--------- TreeSet Demo ---------");
        TreeSetDemo();

    }
}
