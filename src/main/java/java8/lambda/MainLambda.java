package java8.lambda;

import java.util.stream.IntStream;

public class MainLambda {
    public static void main(String[] args) {

        int mult = new LambdaDirector((x, y) -> x * y).doLambda(2, 6);
        System.out.println(mult);

        int mult2 = new LambdaDirector(Math::multiplyExact).doLambda(10, 2);
        System.out.println(mult2);

        int soma = new LambdaDirector((x, y) -> x + y).doLambda(100, 900);
        System.out.println(soma);

        int soma2 = new LambdaDirector(Integer::sum).doLambda(40, 2);
        System.out.println(soma2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Olá mundo!");
            }
        }).run();

        new Thread (() -> System.out.println("Olá mundo!")).run();

        System.out.println("PARENTESES:");
        //PARENTESES() são obrigatórios em 3 situações
        //1. Quando queremos especificar o tipo
        //2. Qunado a expressão lambda receber mais de 1 argumento
        //3. atribuição de exp. lambda em uma variavel (endendi que funciona como se fosse um cast).
        IntStream.range(0,5)
                .filter((int n) -> n == n) // 1
                .reduce((n1, n2)-> n1+n2) //2
                .ifPresent(System.out::println);
        Runnable run = () -> System.out.println("Atribuição do lambda na variavel"); //3
        run.run();

        //CHAVES{}
        //Qando usar chaves é obrigatório o uso do ponto e virgula antes de fechar a chave
        //Quando é esperado um retorno, então deve adicionar o "return"
        //Possibilita escrever expreções com mais de uma linha
        System.out.println("CHAVES:");
        IntStream.range(0,5)
                .filter(n -> {
                    if(n%2==0){
                        System.out.println("este será retornado: " + n);
                        return true;
                    }
                    System.out.println("Este não será retornadao: " + n);
                    return false;
                })
                .forEach(System.out::println);
    }
}
