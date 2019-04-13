package com.lambda.bladi.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class StreamTest {

    private static List<User> users;


    public static  void setUpUser(){
        users = new ArrayList<>();
        users.add(new User(1,"Orco"));
        users.add(new User(2,"Pepe"));
        users.add(new User(3,"Bladi"));
        users.add(new User(4,"Angela"));
        users.add(new User(5,"Bladi"));
        users.add(new User(6,"Mama"));
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


        System.out.println("------- FILTER ---------------");

        //Filter nueva secuencia que parte del stream original que han pasado por una prueba especificada por un predicado
        setUpUser();
        List<User> userFilter = users.stream()
                                     .filter(item -> item.getNombre() != "Ale")
                                     .filter(item -> item.getId()>2)
                                     .collect(Collectors.toList());
        userFilter.stream().forEach(item -> System.out.println("Id: "+item.getId()+"\nName: "+item.getNombre()));

        System.out.println("------- FIND FIRST ---------------");
        setUpUser();
        //orElse en el caso de que el filtro no tenga Orco retorna el nuevo usuario
        User user = users.stream()
                        .filter(item -> item.getNombre().equals("Bladi"))
                        .findFirst()
                        .orElse(new User (7,"OrElse"));
        System.out.println(user.getId()+" "+user.getNombre());

        System.out.println("------- FLAT MAP ---------------");
        //Obtiene los datos de diferentes arrays y los concatena en un único stream
        //Una vez recopilados todos los datos en un mismo flujo podremos filtrar o lo que queramos.
        List<List<String>> allList = new ArrayList<List<String>>(
                Arrays.asList(
                        new ArrayList<String>(Arrays.asList("Bladi","Rober","Pili")),
                        new ArrayList<String>(Arrays.asList("Nocillo","Jaime","Guindi"))));
        List<String> nameUniqueList = allList.stream()
                .flatMap(item -> item.stream())
                .collect(Collectors.toList());
        nameUniqueList.stream().forEach(item -> System.out.println(item));
    }

}
