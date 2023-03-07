package dio.padroes.java.puro;

import dio.padroes.java.puro.facade.Facade;
import dio.padroes.java.puro.singleton.SingletonEager;
import dio.padroes.java.puro.singleton.SingletonLazy;
import dio.padroes.java.puro.singleton.SingletonLazyHolder;
import dio.padroes.java.puro.strategy.*;

public class Test {
    public static void main(String[] args) {
        //Singleton
        System.out.println("Lazy");
        SingletonLazy lazy = SingletonLazy.getInstancia();
        System.out.println(lazy);
        lazy = SingletonLazy.getInstancia();
        System.out.println(lazy);

        System.out.println("Eager");
        SingletonEager eager = SingletonEager.getInstancia();
        System.out.println(eager);
        eager = SingletonEager.getInstancia();
        System.out.println(eager);

        System.out.println("LazyHolder");
        SingletonLazyHolder holder = SingletonLazyHolder.getInstancia();
        System.out.println(holder);
        holder = SingletonLazyHolder.getInstancia();
        System.out.println(holder);

        //Strategy
        System.out.println("Strategy");
        Comportamento normal = new ComportamentoNormal();
        Comportamento defensivo = new ComportamentoDefensivo();
        Comportamento agressivo = new ComportamentoAgressivo();
        Robo robo = new Robo();

        robo.setComportamento(normal);
        robo.mover();
        robo.setComportamento(defensivo);
        robo.mover();
        robo.setComportamento(agressivo);
        robo.mover();
        robo.mover();

        //Facade
        System.out.println("Facade");

        Facade facade = new Facade();
        facade.migrarCliente("Adriano","39510000");


    }
}
