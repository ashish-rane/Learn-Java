package com.xpert.java;


class SampleClass{

    // Constants
    public  static final int INT_CONSTANT = 1;
    public static final String STRING_CONSTANT = "Some string const";

    // Class member
    public static String classVar;


    // Instance variable
    private int privVar; // only accessible in the class methods
    protected int protVar; // accessible in other classses in package and subclasses (even in other packages)
    int packVar; // default package access
    public  int pubVar ; // everywhere

    // Static block to initialize static (class) variables
    static{
        classVar = "Some Class Var";
    }

    // Constructor
    public SampleClass(){
        privVar = 1;
        protVar = 2;
        pubVar = 3;
        packVar = 4;
    }

    // Getter
    public int getPrivVar() {
        return privVar;
    }

    // Setter
    public void setPrivVar(int privVar) {
        this.privVar = privVar;
    }

    // Method overload.
    // Return value not part of method overload
    public void sampleMethod(){
        System.out.println("Sample Method: No parameters");
    }

    public void sampleMethod(String param){
        System.out.println("Sample Method: " + param);
    }

    public void sampleMethod(float param){

        int locaVar = 100;
        System.out.println("Sample Method: " + Float.toString(param));
        System.out.println("Local Variable " + locaVar);
    }
}

public class ClassDemo {

    public static void main(String[] args){
        System.out.println("Hello World!");

        SampleClass obj =  new SampleClass();
        // Access public
        System.out.println("Private Member " + obj.getPrivVar());
        System.out.println("Protected Member " + obj.protVar);
        System.out.println("Package Member " + obj.packVar);
        System.out.println("Public Member " + obj.pubVar);

        // Static block
        System.out.println("Class Variable " + SampleClass.classVar);

        // Overload
        obj.sampleMethod();
        obj.sampleMethod("parameter");
        obj.sampleMethod(1.2f);

    }
}
