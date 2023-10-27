package java8.iterables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainIterables {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        //Não é necessario usar um Stream, o list (todos os Iterable) já tem uma implementação do forEach.
        System.out.println("\n Iterable.foreach(Consumer)");
        list.forEach(System.out::print);

        System.out.println("\n Collection.removeIf(Predicate)");
        list.removeIf(n -> n%2 == 0);    //Itera sobre a lista e caso o predicate retorne true, o elemento é removido.
        list.forEach(n-> System.out.print(n+" "));

        System.out.println("\n List.replaceAll(UnaryOperator)");
        list.replaceAll(n-> n*100);
        list.forEach(n-> System.out.print(n+" "));

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"um");
        map.put(2,"dois");
        map.put(3,"três");
        map.put(4,"quatro");

        System.out.println("\n HashMap.forEach(BiConsumer)");
        map.forEach((k,v)-> System.out.print(k+v+" "));

        System.out.println("\n HashMap.compute(K, BiConsumer)");
        map.compute(2,(k, v) -> v + " VEZES UM"); // altera ou cria o elemento nesta possição (alterou V da key 2).
        map.compute(0,(k, v) -> v + " VEZES UM"); // altera ou cria o elemento nesta possição (key 0 não existia).
        map.forEach((k,v)-> System.out.print(k+v+" "));

        System.out.println("\n HashMap.compute(K, V, BiFunction)");
        map.merge(4, "CINCO", //Se o Key já tem um valor então a BiFunction é executada
                (mapValue,  //mapValue: valor correspondente a key no map.
                 value)     // value: segundo argumento do método merge
                        -> mapValue + value + " MENOS 41"); //corpo da BiFunction
        map.merge(5, "CINCO", //Neste caso o map não tem uma key 5, e por isso cria ela com o valor de "value"
                (mapValue, value)-> mapValue + value + "MENOS 41"); //está linha não é executada neste exemplo.
        map.forEach((k,v)-> System.out.print(k+v+" "));

        System.out.println("\n HashMap.replaceAll(BiFunction)");
        map.replaceAll((k,v)-> (v+" MAIS "+k+" = "+ (k+k)));
        map.forEach((k,v)-> System.out.println("Key: "+k+" conta: "+ v));
    }
}
