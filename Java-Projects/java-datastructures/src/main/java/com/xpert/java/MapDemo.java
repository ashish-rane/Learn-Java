package com.xpert.java;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Map provides data structure to store key-value pairs.
 * AbstractMap implements Map interface
 */
public class MapDemo {

    /**
     * Extends AbstractMap.
     * Uses Hashtable structure for constant access times.
     * Similart to legacy HashTable, but HashTable class is synchronized.
     * HashMap is not synchronized.
     */
    public static void HashMapDemo(){
        HashMap<String,Double> hm = new HashMap<>();

        // Put elements to the map
        hm.put("Zara", new Double(3434.34));
        hm.put("Mahnaz", new Double(123.22));
        hm.put("Ayan", new Double(1378.00));
        hm.put("Daisy", new Double(99.22));
        hm.put("Qadir", new Double(-19.08));

        System.out.println("To String " + hm);

        System.out.println("Contains Key Ayan " + hm.containsKey("Ayan"));
        System.out.println("Contains Value " + hm.containsValue(99.22));
        System.out.println("Size " + hm.size());
        System.out.println("Keys " + hm.keySet());
        System.out.println("Values " + hm.values());
        System.out.println("Entry Set " + hm.entrySet());
        System.out.println("Get Value for Zara " + hm.get("Zara"));
        hm.remove("Qadir");
        System.out.println("After Removing Qadir" + hm);
        hm.replace("Mahnaz", 200.0);
        System.out.println("After replacing value of Mahnaz "  + hm);

        hm.clear();
        System.out.println("Is Empty " + hm.isEmpty());
    }

    /**
     * Implements the SortedMap interface.
     * Stores the key value pairs in a tree structure in a sorted order.
     * which results in efficient retrieval.
     *
     */
    public static void TreeMapDemo(){

        TreeMap<String, Double> tm = new TreeMap<>();
        tm.put("Zara", new Double(3434.34));
        tm.put("Mahnaz", new Double(123.22));
        tm.put("Ayan", new Double(1378.00));
        tm.put("Daisy", new Double(99.22));
        tm.put("Qadir", new Double(-19.08));

        System.out.println("To String " + tm);
        System.out.println("Size " + tm.size());
        System.out.println("First Key" + tm.firstKey());
        System.out.println("Last Key " + tm.lastKey());
        System.out.println("Floor Daisy " + tm.floorKey("Daisy"));
        System.out.println("Ceiling Daisy " + tm.ceilingKey("Daisy"));
        System.out.println("Floor Entry Daisy " + tm.floorEntry("Daisy"));
        System.out.println("Ceiling Entry Daisy " + tm.ceilingEntry("Daisy"));
        System.out.println("Higher Key Daisy " + tm.higherKey("Daisy"));
        System.out.println("Lower Key Daisy " + tm.lowerKey("Daisy"));
        System.out.println("Higher Entry Daisy " + tm.higherEntry("Daisy"));
        System.out.println("Lower Entry Daisy " + tm.lowerEntry("Daisy"));
        System.out.println("Contains Key Zara" + tm.containsKey("Zara"));
        System.out.println("Contains Value 123.22" + tm.containsValue(123.22));
        tm.remove("Qadir");
        System.out.println("After removing Qadir " + tm);

        System.out.println("Keys " + tm.keySet());
        System.out.println("Values " + tm.values());
        System.out.println("Head Map Daisy " + tm.headMap("Daisy") );
        System.out.println("Tail Map Daisy " + tm.tailMap("Daisy"));
        System.out.println("Sub Map Mahnaz-Daisy " + tm.subMap("Daisy", "Zara"));
        tm.replace("Daisy", 500.00 );
        System.out.println("After replace Daisy value to 500.00 " + tm);

        System.out.println("Get Value for Zara " + tm.get("Zara"));
        tm.clear();

        System.out.println("Is Empty " + tm.isEmpty());
    }

    public static void main(String[] args){

        System.out.println("----------- HashMap DEMO -----------");
        HashMapDemo();

        System.out.println("----------- TreeMap DEMO -----------");
        TreeMapDemo();
    }
}
