package java8.stream.collect;

import java.util.*;
import java.util.stream.Collectors;
/*
 *  O método collect recebe 3 argumentos:
 * supplier: define qual será o tipo do retorno do collect.
 * accumulator: este deve fornecer uma metodo que vincule(fold) o elemento1 (uma List<String> neste exemplo)
 *               ao elemento2 (uma String neste exemplo). O elemento1 retornará a ser o element1 na proxima execução,
 *               e o elemento2 o proximo elemento da lista (list).
 * combiner: recebe os resultados(elementos1) do accumullator, deve combinalos e retornar estes dados em um objeto da
 *               instância definida no Supplier.
 * */
public class MainCollect {
    public static void main(String[] args){
        List<String> list = Arrays.asList("Lista","de","palavras","sem","um","separador","entre","as","palavras",".");

        //Exemplo do uso do
        ArrayList<String> collect = list.stream()
                //.parallel() //com ou sem esta linha o resultado deve sempre ser o mesmo
                .collect(
                        ArrayList::new,
                        (lista, elemento) -> {
                            lista.add(elemento);
                            if(elemento.equals(".")) {
                                return;
                            }
                            lista.add(" ");
                        },
                        ArrayList::addAll
                );
        System.out.println("Adicionando espaços entre as palavras");
        System.out.println("Antes: " + list);
        System.out.println("Depois: " + collect);


       List<String> list1 = list.stream()
                .collect(
                        () -> new ArrayList<>(),
                        (e1, e2) -> e1.add(e2), //vincula o
                        (e1, e2) -> e1.addAll(e2) // retorna List<>
                );
        System.out.println("\nlist1: " + list1);

        //Codigo anterior escrito utilizando "method reference"
        List<String> listMR = list.stream()
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        System.out.println("listMR: " + listMR);

        //Codigo anterior utilizando Collectors.toList()
        List<String> list2 = list.stream()
                .collect(Collectors.toList());
        System.out.println("list2: " + list2);

        //Codigo anterior transformando o list em um Set
        HashSet<String> set = list.stream()
                .collect(
                        HashSet::new,
                        HashSet::add,
                        HashSet::addAll
                );
        System.out.println("\nset: " + set);
        //Codigo anterior utilizando Collectors.toSet()
        Set<String> set1 = list.stream()
                .collect(Collectors.toSet());
        System.out.println("set1: " + set1);

        //Para uma maior flexibilidade utilize o Collectors.toCollection() e informe qual o tipo de retorno desejado.
        //TreeSet
        Set<String> treeSet = list.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("\ntreeSet: " + treeSet);
        //ArrayDeque
        Deque<String> arrayDeque = list.stream()
                .collect(Collectors.toCollection(ArrayDeque::new));
        System.out.println("arrayDeque: " + arrayDeque);

        //Juntando todos os elementos da lista em uma unica String.
        String join = list.stream()
                .collect(Collectors.joining());
        System.out.println(join);

        //Juntando todos os elementos da lista em uma unica String, porém adicionando um separador entre cada elemento.
        String joinComSeparador = list.stream()
                .map(e -> e.equals("sem") ? "com" : e) //trocando a palavra "sem" por "com", apenas para fazer sentido
                .collect(Collectors.joining(" ")); //juntando e adicionando o espaço em branco entre os elementos
        System.out.println(joinComSeparador);

        List<Integer> listInteger = Arrays.asList(1,2,3,4,5,6,7,8,9);

        //Collectors.averaging...() calcuma a média de uma lista/stream de números.
        // Existem os metodos para os tipos primitivos: int, double e long.
        Double avg = listInteger.stream()
                .collect(Collectors.averagingInt(Integer::intValue));//aqui deve passar um valores int, nossa lista é de Integer.
        System.out.println(avg);

        //Collectors.summing...() soma os valores de uma lista/stream de números.
        // Existem os metodos para os tipos primitivos: int, double e long.
        int sum = listInteger.stream()
                .collect(Collectors.summingInt(Integer::intValue));//aqui deve passar um valores int, nossa lista é de Integer.
        System.out.println(sum);

        //Collectors.summarizing...() retorna um objeto que detem varios calculos, seguem alguns exemplos no print.
        // Existem os metodos para os tipos primitivos: int, double e long.
        IntSummaryStatistics summaryStatistics = listInteger.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));//aqui deve passar um valores int, nossa lista é de Integer.
        System.out.println("summaryStatistics");
        System.out.println("count: " + summaryStatistics.getCount());
        System.out.println("max: " + summaryStatistics.getMax());
        System.out.println("min: " + summaryStatistics.getMin());
        System.out.println("sum: " + summaryStatistics.getSum());
        System.out.println("avg: " + summaryStatistics.getAverage());

        //Collectors.counting()
        Long count = listInteger.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //CoCollectors.minBy() ou maxBy()
        //Necessario informar o criterio de comparação dos elementos, neste caso usamos a ordem natural dos números.
        listInteger.stream()
                .collect(Collectors.minBy(Comparator.naturalOrder()))
                .ifPresent(System.out::println);

        //GroubingBy: retorna um Map<KeyType, List<ValueType>> onde:
        //KeyType: tipo retornado pela função lambda inserida com argumento do método Collectors.groupingBy()
        //          neste exemplo (n)->n%3 ("n%3" resto da divisão por 3) retorna um Integer.
        //ValueType: tipo do elemento da stream.
        Map<Integer, List<Integer>> map1 = listInteger.stream()
                .collect(Collectors.groupingBy((n) -> n % 3));
        System.out.println("groupingBy:\n" + map1);

        listInteger = Arrays.asList(11,12,14,34,35,37,38,66,67,63,91,94,95,99);
        Map<Character, List<Integer>> map3 = listInteger.stream()
                .collect(Collectors.groupingBy((n) -> n.toString().charAt(0)));
        System.out.println("groupingBy3:\n" + map3);

        //PartitioningBy: retorna um Map<Boolean, List<ValueType>> onde:
        //KeyType: sempre deverá ser um Boolean. Logo a expressão lambda deve retornar um Boolean também.
        //ValueType: tipo do elemento da stream.
        //Este exemplo separa os números divisiveis por 3.
        Map<Boolean, List<Integer>> map2 = listInteger.stream()
                .collect(Collectors.partitioningBy((n) -> n%3 == 0));
        System.out.println("partitioningBy:\n" + map2);

        //ToMap:retorna um Map<KeyType, ValueType> serão definidos respectivamente pela primeira e segunda expresões
        //lambdas recebidas como parametro toMap(KeyType, ValueType)
        //Este exemplo separa os números divisiveis por 3.
        Map<Integer, Double> map4 = listInteger.stream()
                .collect(Collectors.toMap(
                        n -> n, //retorna o próprio número, de tipo Integer
                        n -> n.doubleValue()/2));  //retorna o número dividido por 2, retorno do tipo Double.
        System.out.println("toMap:\n" + map4);

    }
}
