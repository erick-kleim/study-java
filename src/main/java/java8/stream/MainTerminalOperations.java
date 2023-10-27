package java8.stream;

import java.util.*;
import java.util.stream.Collectors;

public class MainTerminalOperations {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,1,2,2,3,4,5,6,7,8,9);

        //Conta quantos elementos existem na lista
        System.out.println("\nOperação terminal: count");
        long count = list.stream()
                .filter(e -> e < 3)
                .count();// Operação terminal: conta quantos elementos passaram pelo filtro
        System.out.println(count);

        //Retorna o menor elemento da lista
        System.out.println("\nOperação terminal (Redução): min");
        Optional<Integer> min = list.stream()
                .min(Comparator.naturalOrder());// Operação terminal: é preciso informar como encontrar o menor elemento
        System.out.println(min.get());

        //Retorna o maior elemento da lista
        System.out.println("\nOperação terminal (Redução): max");
        Optional<Integer> max = list.stream()
                .max(Comparator.naturalOrder());// Operação terminal: é preciso informar como encontrar o maior elemento
        System.out.println(max.get());

        //Retorna uma lista
        System.out.println("\nOperação terminal: collect");
        List<Integer> collect = list.stream()
                .filter(e -> e > 5)
                .map(e -> e*3)
                .collect(Collectors.toList());// Operação terminal: retorna os elementos que passaram pelo filtro e pelo map em um objeto do tipo List
        collect.forEach(System.out::println);

        //Retorna um Map<Boolean, List<Object>> agrupado valores pares e impares
        System.out.println("\nOperação terminal: collect");
        Map<Boolean, List<Integer>> collect2 = list.stream()
                .distinct()
                .collect(Collectors.groupingBy(e -> e % 2 == 0));// Operação terminal: para cada elemento faz uma validação e agrupa o elemento dado o resultado booleano
        System.out.println(collect2);

        //Retorna um Map<Integer, List<Object>> agrupado valores pelo resto da divizão por 4
        System.out.println("\nOperação terminal: collect");
        Map<Integer, List<Integer>> collect3 = list.stream()
                .distinct()
                .collect(Collectors.groupingBy(e -> e % 4));// Operação terminal: para cada elemento faz calculo e agrupa o elemento dado o resultado Integer
        System.out.println(collect3);

        //Concatena os valores da stream em uma String
        System.out.println("\nOperação terminal: collect");
        String collect4 = list.stream()
                .map(e -> String.valueOf(e))//ou .map(String::valueOf) Transforma o stream de Integer em straem de String
                .collect(Collectors.joining());// Operação terminal: concatena os valores do stream em uma String
        System.out.println(collect4);

        //Concatena os valores da stream em uma String, porém com um separador para cada elemento
        System.out.println("\nOperação terminal: collect");
        String collect5 = list.stream()
                .map(e -> String.valueOf(e))//ou .map(String::valueOf) Transforma o stream de Integer em straem de String
                .collect(Collectors.joining(";"));// Operação terminal: concatena os valores adicionando um separador
        System.out.println(collect5);
    }
}
