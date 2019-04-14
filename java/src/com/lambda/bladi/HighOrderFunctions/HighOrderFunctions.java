package com.lambda.bladi.HighOrderFunctions;


//Función que puede recibir una función como parámetro de entrada o dos y devuelve una función en el return de la misma


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements SumInterface {
    public int sum(int a, int b){
        return a+b;
    }


    //Primer parámetro interfaz
    public int sumHighOrderFun(SumInterface sum,int a, int b){
        return sum.sumInterface(a,b);
    }


    //Interfaz funcional
    public static void showCapitaLetters(Function<String,String> function, String nombre){
        System.out.println(function.apply(nombre));
    }

    //Interfaz Functional Consumer<T>
    public static void filterConsumer(List<String> list, Consumer<String> consumer, int maxChar){
        list.stream().filter(logicPredicate(maxChar)).forEach(consumer);
    }

    public static Predicate<String> logicPredicate(int maxCharacteres){
        return item->item.length()<maxCharacteres;
    }

    public static void main (String args[]){
        HighOrderFunctions hof = new HighOrderFunctions();
        System.out.println("Función normal: "+hof.sum(1,2));
        System.out.println("Función implementada por una interfaz: "+hof.sumInterface(1,2));
        SumInterface sumInterface = (a, b) -> (a+b);
        System.out.println("High Order Function: "+hof.sumHighOrderFun(sumInterface,1,2));

        System.out.println("Interfaz Funcional Function<T,R>");
        /*
        interface Function<T t, R t>{
        R apply(T t)
        }
        *
        * */
            Function<String,String> showCL = s -> s.toUpperCase();
            showCapitaLetters(showCL,"bladimir") ;

        System.out.println("Interfaz BiFunction<T,U,R>");
        /*
        interface BiFunction<T,U,R>{
            R apply(T t,U u)
        }
        * */
        System.out.println("Interfaz Functional Predicate<T>");
        /*
        interface Predicate<T>{
            Boolean text (T t)
        }
        * */

        List<Integer> num = Arrays.asList(1,34,-23,-45,55,7,87,-2,90,3,34,-88);
        //Eliminamos los números negativos.
        BiFunction<
                List<Integer>,
                Predicate<Integer>,
                List<Integer>
                > filter = (integers, integerPredicate) -> integers.stream().filter(item -> integerPredicate.test(item)).collect(Collectors.toList());
        System.out.println(filter.apply(num,item->item>0));


        System.out.println("Interfaz Functional Consumer<T>");
        /*
        interface Consumer<T>{
            void accept (T t)
        }
        * */
        List<String> names = new ArrayList<>();
        names.add("Bladimir");
        names.add("Angela");
        names.add("Rober");

        filterConsumer(names,item->System.out.println(item),6);

        hof.logicPredicate(4);



















    }


    @Override
    public int sumInterface(int a, int b) {
        return a+b;
    }
}
