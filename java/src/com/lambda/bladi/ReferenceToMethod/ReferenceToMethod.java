package com.lambda.bladi.ReferenceToMethod;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

/*
   Tipos de métodos de  Referencia
   Tipo                                      Syntax                Method Reference                 Lambda expresion
   ------------------------------------------------------------------------------------------------------------------------------
   Referencia a                            Class::staticMethod     Math::abs                        n -> Math.abs(n)
   static method
   ------------------------------------------------------------------------------------------------------------------------------
   Referencia a un                    instancia::metodoInstancia    s:toString                      () -> ""string".toString()
   método de instancia
   de un objeto
   particular
   -------------------------------------------------------------------------------------------------------------------------------
   Referencia a un método              Class: metodoInstancia       String:toString                  s -> s.toString()
   de instancia de un objeto
   arbitrario de un tipo
   particular
   ---------------------------------------------------------------------------------------------------------------------------------
   Referencia a un constructor         Class::new                   String::new                       () -> new String
   */
public class ReferenceToMethod {


    public static void main(String[] args) {
        /*
        *  Referencia a                            Class::staticMethod     Math::abs                        n -> Math.abs(n)
           static method
        * */
        Work work = new Work() {
            @Override
            public void action() {
                User.referenceToStaticMethod();
            }
        };
        work.action();
        Work workL = () -> User.referenceToStaticMethod();
        workL.action();
        Work workMR = User::referenceToStaticMethod;
        workMR.action();

        System.out.println("-----------------------------------------------------------------------------------------");

        /*
        Referencia a un                    instancia::metodoInstancia    s:toString                      () -> ""string".toString()
        método de instancia
        de un objeto
        particular
        * */

        User user = new User("Bladi");
        Work workL2 = () -> user.referenceToParticularMethod();
        workL2.action();
        Work workMR2 = user::referenceToParticularMethod;
        workMR2.action();

        System.out.println("-----------------------------------------------------------------------------------------");

        /*
        *  Referencia a un método              Class: metodoInstancia       String:toString                  s -> s.toString()
           de instancia de un objeto
           arbitrario de un tipo
           particular
        * */
        System.out.println("Referencia a un método de instancia de un objeto arbitrario de un tipo particular");
        WorkString workString = word -> word.toUpperCase();
        WorkString workStringRM = String::toUpperCase;
        System.out.println(workString.action("bladi"));
        System.out.println(workStringRM.action("bladi"));

        List<User> listUser = new ArrayList<>();
        listUser.add(new User("Bladi"));
        listUser.add(new User("Alberto"));
        listUser.add(new User("Jaime"));
        listUser.add(new User("Toni"));
        System.out.println("--- forEach");
        listUser.forEach(item -> item.showName());
        System.out.println("--- Método  referencia");
        listUser.forEach(User::showName);

        System.out.println("-----------------------------------------------------------------------------------------");
        /*
        * Referencia a un constructor         Class::new                   String::new                       () -> new String
        * */
        System.out.println("Referencia a constructor");
        User user1 = new User("Roberto");
        IUser iuser = user2 -> new User("Roberto");
        User userCreated = iuser.create("");
        userCreated.showName();

        IUser iuser2 = User::new;
        User userCreated2 = iuser2.create("Bladi");
        userCreated2.showName();
    }
}
