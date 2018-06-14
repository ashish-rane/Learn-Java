package com.xpert.java;

class Test{
    private  int member1;
    private  int member2;

    public  Test(int m1, int m2){
        member1 = m1;
        member2 = m2;
    }

    @Override
    public boolean equals(Object obj) {
        Test other = (Test)obj;
        return member1 == other.member1 && member2 == other.member2;
    }
}

public class OperatorsDemo {

    public static  void main(String[] args){
        int var = -4;
        System.out.println(String.format("1 == 2 => %b" , 1 == 2));
        System.out.println(String.format("1 <= 2 => %b" , 1 <= 2));
        System.out.println(String.format("1 > 2 => %b" , 1 > 2));
        System.out.println(String.format("1 & 2 => %d" , 1 & 2));
        System.out.println(String.format("1 == 2 => %d" , 1 ^ 2));
        System.out.println(String.format("~2 => %d" , ~2));
        System.out.println(String.format("-4 << 2 => %d" ,    var << 2));
        System.out.println(String.format("-4 >> 2 => %d" ,    var >> 2));
        System.out.println(String.format("-4 >>> 2 => %d" ,    var >>> 2));

        Test t1 = new Test(1,2);
        Test t2 = new Test(1,2);
        Test t3 = t1;

        Test t4 = new Test(3,4);

        System.out.println(String.format("t1 == t2 >> %b", t1 == t2)); // returns false even if values are same, since it refers different objects
        System.out.println(String.format("t1 == t3 %b", t1 == t3)); // returns true since object referred is the same.
        System.out.println(String.format("t1 == t4 %b", t1 == t4));

        System.out.println(String.format("t1.equals(t2) >> %b", t1.equals(t2))); // true due to the overriden equals method, otherwise default is false.
        System.out.println(String.format("t1.equals(t3) >> %b", t1.equals(t3))); // values are equal so true
        System.out.println(String.format("t1.equals(t4) >> %b", t1.equals(t4)));

        // New For loop
        String[] colors = {"red", "blue", "green"};

        for(String color: colors){
            System.out.println("Colors : " + color);
        }

        // Boxed Types
        Integer boxedInt = new Integer(500);
        int val = boxedInt.intValue();
        System.out.println("Compare to " + boxedInt.compareTo(new Integer(300)));
        Integer boxedInt2 = Integer.valueOf(400);

        int parsedInt = Integer.parseInt("200");

        System.out.println(String.format("min(1,2) >> %d", Integer.min(1,2)));
        System.out.println(String.format("max(1,2) >> %d", Integer.max(1,2)));
        System.out.println(String.format("pow(2,4) >> %f", Math.pow(2,4)));
    }
}
