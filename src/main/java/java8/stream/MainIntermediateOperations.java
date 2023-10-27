package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MainIntermediateOperations {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,1,2,2,3,4,5,6,7,8,9);

//        list.stream().forEach(e -> System.out.println(e));
//        list.stream().forEach(System.out::println);
//        list.forEach(System.out::println);

        //Pula os N primeiros elementos
        System.out.println("\nOperação intermediaria: skip");
        list.stream()
                .skip(2) // Operação intermediaria: pulando 2 primeiros elementos
                .forEach(System.out::println);

        //Limita a lista a apenas os N primeiros elementos
        System.out.println("\nOperação intermediaria: limt");
        list.stream()
                .limit(3) // Operação intermediaria: limitando aos 3 primeiros elementos
                .forEach(System.out::println);

        //Remove os elementos repetidos
        System.out.println("\nOperação intermediaria: distinct (usa equals e hashcode)");
        list.stream()
                .distinct() //Operação intermediaria
                .forEach(System.out::println);

        System.out.println("\nOperação intermediaria: filter (aceita um lambda de retorno booleano)");
        //Filtra os elementos dado uma condição booleana para cada elemento
        list.stream()
                .filter(e -> e % 2 == 0) //Operação intermediaria: retorna true para cada elemento par, ou seja resto da divisão por 2 igual a 0.
                .forEach(System.out::println);

        //filtra e mostra apenas elementos sem repeti-los
        System.out.println("\nOperação intermediaria: filter (aceita um lambda de retorno booleano)");
        list.stream()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer % 2 == 0;
                    }
                }) //Operação intermediaria
                .forEach(System.out::println);

        //Altera o valor de cada elemento e mostra (Não altera a lista original)
        System.out.println("\nOperação intermediaria: map");
        list.stream()
                .map(e -> e * 2) //Operação intermediaria (multiplicando por 2)
                .forEach(System.out::println);


    }
}
