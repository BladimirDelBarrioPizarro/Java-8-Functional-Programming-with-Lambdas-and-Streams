package com.lambda.bladi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

public class Lambda implements Default {

    public void order() {
        List<String> persons = new ArrayList<>();
        persons.add("Hello");
        persons.add("Lambda");
        persons.add("Bladi");


        //Enfoque imperativo
        Collections.sort(persons, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });


        for (String element : persons) {
            System.out.print(element);
        }

        //Lambda
        Collections.sort(persons,(String p1,String p2) -> p1.compareTo(p2));

        for (String element : persons) {
            System.out.print(element);
        }
    }

    public void calculateParams(){
        Operation operation = new Operation() {
            @Override
            public double calculateParams(double p1, double p2) {
                return (p1+p2)/2;
            }
        };

        System.out.println("Operation: "+operation.calculateParams(5,5));

        //Lambda
        Operation operation1 = (double y, double x) -> (x+y)/2;

        System.out.println("Operation: "+operation.calculateParams(2,3));
    }





    public static void main(String[] args) {
        System.out.println(System.getProperty("java.runtime.version"));
        Lambda lambda = new Lambda();
        lambda.order();
        lambda.calculateParams();

        MyName myName = () -> "Bladi Lamdba";

        MyName myNameIs = new MyName() {
            @Override
            public String myName() {
                return "Bladi Anónimo";
            }
        };


        System.out.println(myName.myName());
        System.out.println(myNameIs.myName());


        Sum sum = (a, b) -> a+b;
        System.out.println(sum.suma(2,4));

        Sum suma2 = (a, b) -> {
          a = b+2;
          a += a+b;
          System.out.println("Mensaje dentro de lambda");
          return a;
        };

        System.out.println(suma2.suma(2,2));
        System.out.println("--------------------------------------------------------------------");

        Lambda lambda2 = new Lambda();
        System.out.println(lambda2.nameDefault("Bladi por Defecto"));



    }



    @Override
    public void showName(String name) {

    }
}