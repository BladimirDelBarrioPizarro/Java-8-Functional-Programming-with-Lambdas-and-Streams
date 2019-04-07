package com.lambda.bladi.Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    private static List<User> users;


    public static  void setUpUser(){
        users = new ArrayList<>();
        users.add(new User(1,"Bladi"));
        users.add(new User(2,"Pepe"));
        users.add(new User(3,"Ale"));
        users.add(new User(4,"Cover"));
    }


    // forEach
    private static void showList(){
        users.stream().forEach(item->System.out.println(item.getId()+" "+item.getNombre()));
    }

    public static void main(String args[]){
        setUpUser();
        //Stream stream = Stream.of(users);
        //users.stream();


        users.stream().forEach(users -> users.setNombre(users.getNombre()+" Apellido"));
        showList();

        System.out.println("------- MAP ---------------");

        //Map Transformación del flujo original stream a una lista String con el nombre de todos los usuarios
        List<String> list = users.stream().map(item -> item.getNombre()).collect(Collectors.toList());
        list.stream().forEach(item -> System.out.println(item));









    }


}
