package com.lambda.bladi.HighOrderFunctions;


//Función que puede recibir una función como parámetro de entrada o dos y devuelve una función en el return de la misma


import java.util.function.Function;

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

    public static void main (String args[]){
        HighOrderFunctions hof = new HighOrderFunctions();
        System.out.println("Función normal: "+hof.sum(1,2));
        System.out.println("Función implementada por una interfaz: "+hof.sumInterface(1,2));
        SumInterface sumInterface = (a, b) -> (a+b);
        System.out.println("High Order Function: "+hof.sumHighOrderFun(sumInterface,1,2));

        System.out.println("Interfaz Funcional Function<T,R>");
        /*
        * interface Function<T t, R t>{
        * R apply(T t)
        * }
        *
        * */
            Function<String,String> showCL = s -> s.toUpperCase();
            showCapitaLetters(showCL,"bladimir") ;

    }


    @Override
    public int sumInterface(int a, int b) {
        return a+b;
    }
}
