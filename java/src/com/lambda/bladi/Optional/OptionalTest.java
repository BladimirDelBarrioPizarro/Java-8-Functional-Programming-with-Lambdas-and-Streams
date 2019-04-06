package com.lambda.bladi.Optional;




import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {


    public static void main(String arg[]){

          //  testOptional(null);
        // orElseOptional("Bladi");
       //   orElseOptional(null);
        // orElseThrow("Vladi");
       // orElseThrow(null);
        isPresent("Bladi");
        isPresent(null);
    }



    public static void testOptional(String name){
        System.out.println(name.length());
    }

    public static void createOptional(){
        Optional<String> optional = Optional.empty();
        optional.get();
    }

    public static void orElseOptional(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
       // Optional<String> optional1 = Optional.of(nombre);

        String nameOfNullable = optional.orElse("empty");
       // String nameOf = optional1.orElse("empty");
        System.out.println(nameOfNullable);
       // System.out.println(nameOf);
     }


     public static void orElseThrow(String name){
        Optional<String> optional = Optional.ofNullable(name);
        optional.orElseThrow(NullPointerException::new);
        String nameOptional = optional.get();
        System.out.println(nameOptional);
     }

     public static void isPresent(String name){
        Optional<String> optional = Optional.ofNullable(name);
        System.out.println(optional.isPresent());
     }
}
