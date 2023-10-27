package java8.stream.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class MainParallel {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);

        System.out.println("\n parallelStream().forEach()");
        list.parallelStream()
                .forEach(System.out::println);
        System.out.println("\n parallelStream().forEachOrdered()");
        list.parallelStream()
                .forEachOrdered(System.out::println);

        System.out.println("\n parallelStream().findAny()");
        //Em uma Stream que não é paralelo, sempre retorna o primeiro elemento.
        Optional<Integer> any = list.parallelStream().findAny();
        any.ifPresent(System.out::println);

        System.out.println("\n parallelStream().unordered()");
        //Sem esta chamada, o skyp, limit e distinct compartilham tais informações entre4 as threads, ja com a chamada...
        list.parallelStream()
                .unordered()
                .skip(1) //Pula o primeiro elemento disponivel do seu pedaço da stream
                .limit(2)  //limita a quaisquer dois elementos disponivel do seu pedaço da stream
                .distinct() // considera apenas os elementos do pedaço em execução na sua thread para fazer o distinct
                .forEach(System.out::println);

        System.out.println("\n parallelStream().collect(toConcurrentMap())");
        //Se a ordem importa não usar o Concurrent.
        //Permite que o Map seja acessado por diferentes threads, o toMap cria um map para cada thread e outro para
        //juntar todos e retornar apenas 1 map.
        ConcurrentMap<Object, Boolean> collect = list.parallelStream()
                .collect(
                        Collectors.toConcurrentMap(n-> n, n-> n%2 == 0)
                );
        System.out.println(collect);

        System.out.println("\n parallelStream().collect(groupingByConcurrent())");
        //Se a ordem importa não usar o Concurrent.
        //Segue a mesma logica do toConcurrentMap(), exemplo anterior.
        ConcurrentMap<Boolean, List<Integer>> groupingBy = list.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(n -> n % 2 == 0)
                );
        System.out.println(groupingBy);
    }
}
