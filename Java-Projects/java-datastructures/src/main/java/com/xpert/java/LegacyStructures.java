package com.xpert.java;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Hello world!
 *
 */
public class LegacyStructures
{
    /**
     * Vector are similar to array but dynamically grow.
     * Are synchronized. Not part of collections framework
     */
    public static void VectorDemo(){
        Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(0,2);
        System.out.printf("2 exists %b\n", v.contains(2));
        Vector<Integer> v2 = (Vector<Integer>)v.clone();
        System.out.println("to String" + v2.toString());

        System.out.printf("Element at index 1\n",v.elementAt(1));
        System.out.printf("Size: %d\n",v.size());
        v.add(5);
        v.add(8);
        v.add(7);

        for(int i : v){
            System.out.print(i);
        }

        v.remove(2); // remove at index
        v.add(10);
        v.remove((Integer)10); // remove an element takes Object as parameter

        System.out.printf("\nIndex of 7 : %d\n", v.indexOf(7));
        System.out.printf("Is Empty %b\n",v.isEmpty());

        v.sort((o1, o2) -> {
            if (o1 < o2)
                return -1;
            else if (o1 > o2)
                return 1;
            return 0;
        });

        v.forEach((e)-> System.out.print(e));
        v.set(1, 5); // replace element at index 1
        System.out.println();
        v.forEach((e)-> System.out.print(e));

        v.clear();
        System.out.printf("\nIs Empty after clear %b\n",v.isEmpty());

    }

    /**
     * The Enumeration interface defines the methods by which you can enumerate
     * (obtain one at a time) the elements in a collection of objects.
     */
    public static void EnumDemo(){
        Enumeration days;
        Vector dayNames = new Vector();

        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();

        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
        }
    }

    public static void BitsetDemo(){

    }

    public static void StackDemo(){
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i< 10; i++){
            stack.push(i);
        }

        System.out.println(stack.toString());
        System.out.println("Peek " + stack.peek());
        System.out.println("Is Empty " + stack.empty());
        System.out.println("Popped element " + stack.pop());
        System.out.println("Size : " + stack.size());
        System.out.println("Search 6: " + stack.search(6));
        System.out.println("contains 7: " + stack.contains(7));
        System.out.println("Get at 4: " + stack.get(4));
        stack.remove(5);
        System.out.println(stack.toString());
    }

    /**
     * Hashtable is now integrated into the collections framework.
     * It is similar to HashMap,
     * but Hashtable is synchronized and HashMap is not synchronized
     */
    public static void HashTableDemo(){
        // Create a hash map
        Hashtable<String, Double> balance = new Hashtable<>();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", new Double(3434.34));
        balance.put("Mahnaz", new Double(123.22));
        balance.put("Ayan", new Double(1378.00));
        balance.put("Daisy", new Double(99.22));
        balance.put("Qadir", new Double(-19.08));

        System.out.println("Contains Ayan " + balance.containsKey("Ayan"));
        System.out.println("Contains Value " + balance.containsValue(1378.00));

        balance.values().forEach(v -> System.out.print(v + " "));
        System.out.println();

        System.out.println("Get value of key Ayan " + balance.get("Ayan"));
        System.out.println("Is Empty " + balance.isEmpty());

        System.out.println("Size before removal" + balance.size());
        balance.remove("Ayan");
        System.out.println("Size after removal" + balance.size());

        System.out.println("to String " + balance.toString());

        System.out.println("Get value of key Zara before replace " + balance.get("Zara"));
        balance.replace("Zara", 200.0);
        System.out.println("Get value of key Zara " + balance.get("Zara"));

        balance.clear();
        System.out.println("Size after clear " + balance.size());
    }

    /**
     * Properties is subclass of Hashtable. it maintains a list of values
     * in which keys and values are both strings.
     */
    public static void PropertiesDemo(){
        Properties capitals = new Properties();
        Set states;
        String str;

        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");

        System.out.println("Get Property for California " + capitals.getProperty("California"));
        //capitals.propertyNames();
        capitals.setProperty("California", "San Jose");
        System.out.println("Get Property for California " + capitals.getProperty("California"));

        try(FileWriter writer = new FileWriter("caps.properties", false)) {
            capitals.store(writer, "Test Comments");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        Properties caps = new Properties();

        try (FileReader reader = new FileReader("caps.properties")){
            caps.load(reader);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("To String " + caps.toString());
    }

    public static void main( String[] args )
    {
        System.out.println(" ----------- VECTOR DEMO --------------");
        VectorDemo();

        System.out.println(" ----------- STACK DEMO --------------");
        StackDemo();

        System.out.println(" ----------- HASHTABLE DEMO --------------");
        HashTableDemo();

        System.out.println(" ----------- Properties DEMO --------------");
        PropertiesDemo();
    }
}
