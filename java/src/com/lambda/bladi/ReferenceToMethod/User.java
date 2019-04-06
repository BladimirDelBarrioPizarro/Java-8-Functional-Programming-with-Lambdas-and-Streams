package com.lambda.bladi.ReferenceToMethod;



public class User {
    public String name;

    public User(String name){
        this.name=name;
    }


    public static void referenceToStaticMethod(){
        System.out.println("Probando referencia a Método Estático");
    }

    public void referenceToParticularMethod(){
        System.out.println("Probando referencia a Método de Objeto Particular");
    }

    public void showName(){
        System.out.println(this.name);
    }



}
