package java8.methodReference;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MainMethodReference {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        System.out.println("Sem Method Reference");
        list.stream()
                .forEach(n-> System.out.println(n));

        System.out.println("Com Method Reference");
        list.stream()
                .forEach(System.out::println);

        System.out.println("(Meu) Static Method Reference");
        list.stream()
                .map(MainMethodReference::multiplicaPorDois)
                .forEach(System.out::println);

        System.out.println("Static Method Reference");
        list.stream()
                .map(String::valueOf)//n->String.valueOf(n)
                .forEach(System.out::println);

        System.out.println("Cosntructor Method Reference");
        list.stream()
                .map(BigDecimal::new)
                .forEach(System.out::println);

        System.out.println("Method Reference para cada instancia da stram");
        list.stream()
                .map(Integer::doubleValue) // n-> n.doubleValue(), chamando o próprio método.
                .forEach(System.out::println);

        System.out.println("Method Reference para cada instancia da stram");
        list.stream()
                .map(Integer::doubleValue) // n-> n.doubleValue(), chamando o próprio método.
                .forEach(System.out::println);

        System.out.println("Method Reference de uma variavel local");
        BigDecimal multiplicador = new BigDecimal(2);
        list.stream()
                .map(BigDecimal::new)
                .map(multiplicador::multiply)
                .forEach(System.out::println);

    }

    public static Integer multiplicaPorDois(Integer numero){
        return numero*2;
    }
}
