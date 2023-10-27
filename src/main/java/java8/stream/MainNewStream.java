package java8.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainNewStream {
    public static void main(String[] args) throws IOException {
        System.out.println("\n Conversão de Collection para Stream");
        List<Integer> list = Arrays.asList(1,2,3,4);
        list.stream().forEach(System.out::print);
        System.out.println("\nParallel Stream");
        list.parallelStream().forEach(System.out::print);

        System.out.println("\n Conversão de Array para Stream");
        int[] intArray = {1,2,3,4};
        IntStream stream = Arrays.stream(intArray);
        stream.forEach(System.out::print);
        System.out.println("\nParallel Stream");
        IntStream parallelStream = Arrays.stream(intArray).parallel();
        parallelStream.forEach(System.out::print);

        System.out.println("\n Stream.of(T t)");
        // aceita qualquer tipo de objeto.
        Stream.of("Uma", " ", "Stream", " ","de"," ","Strings",".").forEach(System.out::print);
        System.out.println("\nStream com Objetos de tipos diferentes");
        Stream.of(1, " ","Stream com varios tipos", "?", true, 1000.0001).forEach(e-> {
            if(e instanceof String)
                System.out.println("String: "+e);
            else if(e instanceof Integer)
                System.out.println("Integer: "+e);
            else if(e instanceof Double)
                System.out.println("Double "+ e);
            else if(e instanceof Boolean)
                System.out.println("Boolean "+ e);
        });

        System.out.println("\n IntStream.range(int, int):");
        IntStream.range(0,11)//Um array de 0 até 10, não inclui o 11, mas inclui o 0.
                .forEach(System.out::print);

        System.out.println("\n Stream.iterate(seed, Predicate, UnaryOperator)");
        Stream.iterate(2, // Valor inicial da Stream.

                        n-> n<1025,     // FunctionalInterface(Predicate): recebe um valor e retorna um boolean,
                        // quando retornar false, interrompe a iteração.

                        n-> n*2)         // FunctionalInterface(UnaryOperator): recebe um valor e retorna um valor do mesmo tipo.
                // Esta função recebe o valor da "seed" e é retroalimnentado pelo seu próprio retorno.
                .forEach(MainNewStream::sout);

        System.out.println("\n Stream.iterate(seed, UnaryOperator)");
        Stream.iterate(2, // Valor inicial da Stream.
                        // Não existe Predicate, então é necéessario interromper o interate de outra forma.
                        n-> n*2) // FunctionalInterface(UnaryOperator): exatamente como o exemplo anterior.
                .limit(10) //limitando em 10 iterações.
                .forEach(MainNewStream::sout);

        System.out.println("\n BufferedReader.lines()");
        //Cada linha do arquivo é transformado em um elemento da Stream
        File file = new File("src/main/resources/streaminteger.txt");
        file.getClass().getClassLoader();
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        br.lines().forEach(MainNewStream::sout);

        System.out.println("\n Files.list(Path)");
        //Um Stream com todos os nomes de arquivos e diretórios dentro de um Path informado.
        Path p = Path.of("");
        Files.list(p).forEach(MainNewStream::sout);

        System.out.println("\n Random");
        new Random().ints().limit(4).forEach(MainNewStream::sout);

        System.out.println("\n Patter.splitAsStream(String)");
        String frase = "Uma;frase;separada;por;ponto e vírgula";
        Pattern pa = Pattern.compile(";");
        pa.splitAsStream(frase).forEach(MainNewStream::sout);

    }
    public static void sout(Object t){
        System.out.print(t+" ");
    }
}
