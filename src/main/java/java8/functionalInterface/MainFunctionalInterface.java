package java8.functionalInterface;

import java.util.Random;
import java.util.stream.Stream;

public class MainFunctionalInterface {
    public static void main(String[] args) {
        System.out.println("Supplier & Consumer");
        Stream.generate( //generate aceita um Supplier.
                        () -> new Random().nextInt() //um Suplier: método que não recebe nenhum argumento e retorna um valor.
                ).limit(5) //limita o tamanho do stram a 5 elementos.
                .forEach(// forEach aceita um Consumer
                        System.out::println //println é um Consumer: método que recebe um argumento e não retorna nenhu valor
                );

        System.out.println("\nPredicate");
        Stream.generate(() -> new Random().nextInt(3)).limit(5)
                .filter( // aceita um método Predicate
                        (n)-> (n%2==0)  // Predicate: um método que recebe um valor (n) e retorna um boolean,
                        // resultado da comparação (n%2==0).
                ).forEach(System.out::println);

        System.out.println("\nFunction");
        Stream.generate(() -> new Random().nextInt(10)).limit(5)
                .map( // aceita um método Function
                        (n)-> (n.doubleValue())  // Function: um método que recebe um valor (n) e retorna um valor,
                        // neste exemplo retorna o valor da transformação do Inteiro para double (n.toString()).
                ).forEach(System.out::println);

        /* Além dos apresentados aqui, também existem: BiConsumer, BiPredicate e BiFunction (além de outros).
         * A diferença é que cada um destes para o seu pae, é que o método tem um argumento a mais.
         * */

        System.out.println("\nBinaryOperator");
        Stream.generate(() -> new Random().nextInt(10)).limit(5)
                .reduce(// aceita um método BinaryOperator
                        (n1, n2) -> (n1 + n2)   // BinaryOperator: um método que recebe dois valores do mesmo tipo (n1, n2)
                        // e retorna um valor tamém do mesmo tipo, a soma deles (n1 + n2).
                ).ifPresent(System.out::println);
        /* No UnaryOperator o argumento e retorno devem ser do mesmo tipo, a unica diferença é que este tem apenas
         * um arqgumento.
         * */
    }
}
