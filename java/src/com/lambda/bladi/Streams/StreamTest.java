package com.lambda.bladi.Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        users.add(new User(7,"Mama"));
    }


    // forEach mostrar lista
    private static void showList(){
        users.stream().forEach(item->System.out.println(item.getId()+" "+item.getNombre()));
    }

    private static String convertToCapitalLetters(String names){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return names.toUpperCase();
    }

    public static void main(String args[]){
        setUpUser();
        //Stream stream = Stream.of(users);
        users.stream();


        users.stream().forEach(users -> users.setNombre(users.getNombre()+" Apellido"));
        //showList();

        System.out.println("------- MAP ---------------");

        //Map Transformación del flujo original stream a una lista String
        List<String> list = users.stream().map(item -> item.getNombre()).collect(Collectors.toList());
        list.stream().forEach(item -> System.out.println(item));


        System.out.println("------- FILTER ---------------");

        //Filter nueva secuencia que parte del stream original que han pasado por una prueba especificada por un predicado
        //setUpUser();
        List<User> userFilter = users.stream()
                                     .filter(item -> item.getNombre() != "Bladi")
                                     .filter(item -> item.getId()>2)
                                     .collect(Collectors.toList());
        userFilter.stream().forEach(item -> System.out.println("Id: "+item.getId()+"\nName: "+item.getNombre()));

        System.out.println("------- FIND FIRST ---------------");
        //setUpUser();
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

        System.out.println("------- PEEK ---------------");
        setUpUser();
        List<User> user2 = users.stream()
                .peek(item -> item.setNombre(item.getNombre()+" Apellido")).collect(Collectors.toList());

         user2.stream().forEach(item -> System.out.println("Id: "+item.getId()+"\nName: "+item.getNombre()));

        System.out.println("------- COUNT ---------------");
        setUpUser();

        long numFilter = users.stream()
                .filter(item -> item.getId() > 3)
                .count();
        System.out.println(numFilter);

        System.out.println("------- SKIP AND LIMIT ---------------");
        //SKIP : Salta los primeros n elementos
        //LIMIT: Nos limita n elementos
        String [] abc = {"a","b","c","d","e","f","g"};
        List<String> abcFilter = Arrays.stream(abc)
                .skip(2)
                .limit(3)
                .collect(Collectors.toList());
        abcFilter.stream().forEach(item -> System.out.println(item));


        System.out.println("------- SORTED ---------------");
        //Ordenamos la lista de usuarios alfabéticamente
        setUpUser();
        users.stream()
                .sorted(Comparator.comparing(User::getNombre))
                .collect(Collectors.toList());
        showList();



        System.out.println("------- MIN AND MAX ---------------");
        // Usuarios minimos y máximos por id
        setUpUser();
        User userMin = users.stream()
        .min(Comparator.comparing(User::getId))
        .orElse(null);
        System.out.println(userMin.getNombre());
        User userMax = users.stream()
                .max(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMax.getNombre());




        System.out.println("------- DISTINCT ---------------");
        // Quitar los elementos repetidos
        String [] abc2 = {"a","a","b","c","d","e","e","f","g"};
        List<String> abcFilter2 = Arrays.stream(abc2)
                .distinct().collect(Collectors.toList());
        abcFilter2.stream().forEach(item -> System.out.println(item));


        System.out.println("------- ALLMATCH ANYMATCH NONEMATCH ---------------");
        //Allmatch : verifica si el predicado es verdadero
        // AnyMatch : verifica si al menos un valor del predicado es verdadero
        // noneMatch : verifica si ningún elemento pasa el predicado

        List<Integer>  values = Arrays.asList(100,200,300,388,500);

        boolean allMatch = values.stream().allMatch(item -> item > 1);
        System.out.println(allMatch);
        boolean anyMatch = values.stream().allMatch(item -> item > 10000);
        System.out.println(anyMatch);
        boolean noneMatch = values.stream().allMatch(item -> item > 1);
        System.out.println(noneMatch);


        System.out.println("------- SUM AVERAGE RANGE ---------------");
        //Sum: sumar
        //Average : media -> return dooble
        //Range : Rango
        //MapToInt nos transforma la lista de id. Adapta el stream a enteros
        setUpUser();
        double result = users.stream()
                .mapToInt(User::getId)
                .average()
                .orElse(0);
        System.out.println("Media: "+result);

        result = users.stream()
                .mapToInt(User::getId)
                .sum();
        System.out.println("Suma: "+result);
        // Primer parametro como debe empezar segundo donde debe acabar
        System.out.println("Range: "+IntStream.range(0,100).sum());


        System.out.println("------- REDUCE ---------------");
        //Toma el string y los combina en una operación repetida de operación
        setUpUser();
        Integer num = users.stream()
                .map(User::getId)
                .reduce(0,Integer::sum);

        System.out.println("Reduce: "+num);



        System.out.println("------- JOIN ---------------");
        //Devuelve un recopilador que concatena la secuencia de char secuency y devuelve el resultado commo una cadena
        setUpUser();
        String names = users.stream()
                    .map(User::getNombre)
                    .collect(Collectors.joining(" - "))
                    .toString();
           System.out.println(names);



        System.out.println("------- TOSET ---------------");
        //Nos devuelve un colector que acumula los elementos de entrada en un nuevo set. Set no no s garantiza el orden pero si que no haya elelmentos repetidos.
        setUpUser();
        Set<String> setNames =  users.stream()
                .map(User::getNombre)
                .collect(Collectors.toSet());
        setNames.stream().forEach(item -> System.out.println(item));



        System.out.println("------- SUMMARIZING DOUBLE ---------------");
        //Nos devuelve estadísticas Count, sum min, average, max

        setUpUser();
        DoubleSummaryStatistics statistics = users.stream()
                .collect(Collectors.summarizingDouble(User::getId));
        System.out.println(statistics);
        //Otra forma
        DoubleSummaryStatistics statistics2 = users.stream()
            .mapToDouble(User::getId)
            .summaryStatistics();
        System.out.println(statistics2);


        System.out.println("-------  PARTITIONINGBY ---------------");
        //Nos devuelve las listas de elementos la divide en dos una en la que se cumple el predicado y otra en la que no
        setUpUser();
        List<Integer> numList = Arrays.asList(5,7,34,56,2,3,67,4,98);
        Map<Boolean,List<Integer>> isMax = numList.stream()
                .collect(Collectors.partitioningBy(item -> item > 10));

         System.out.println(".. Se cumple el predicado");
         isMax.get(true).stream().forEach(item -> System.out.println(item));
         System.out.println(".. No se cumple el predicado");
         isMax.get(false).stream().forEach(item -> System.out.println(item));


        System.out.println("-------  GROUPINGBY ---------------");
        //Agrupamos según la propiedad
        // Por orden alfabético. Segun el nombre y la letra que especifiquemos nos agrupa dos listas
        setUpUser();
        Map<Character,List<User>> group  = users.stream()
                .collect(Collectors.groupingBy(item -> new Character(item.getNombre().charAt(0))));
        group.get('B').stream().forEach(item -> System.out.println(item.getNombre()));
        group.get('M').stream().forEach(item -> System.out.println(item.getNombre()));



        System.out.println("-------  MAPPING ---------------");
        //Nos transforma una lista de objetos a otra
        setUpUser();
        List<String> persons = users.stream()
                .collect(Collectors.mapping(User::getNombre, Collectors.toList()));
        persons.stream().forEach(item -> System.out.println(item));


        System.out.println("-------  STREAM PARALELO -----------");
        //Forma de ejecutar las operaciones del stream a través de hilos
        setUpUser();
        Long num1 = System.currentTimeMillis();
        users.stream().forEach(item -> convertToCapitalLetters(item.getNombre()));
        Long num2 = System.currentTimeMillis();
        System.out.println("Normal");
        System.out.println(num1);
        System.out.println(num2);
        num1 = System.currentTimeMillis();
        users.parallelStream().forEach(item -> convertToCapitalLetters(item.getNombre()));
        num2 = System.currentTimeMillis();
        System.out.println("Paralelo");
        System.out.println(num1);
        System.out.println(num2);






    }

}
