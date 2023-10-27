package java8.stream.reduce;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*    Funcionamento geral do .reduce()
 * Soma os argumentos e1 e e2, recebem o primeiro e segundo elemento da lista respectivamente, e retorna um resultado do mesmo tipo,
 * o retorno desta execução entra como o argumento e1 na proxima execução do reduce e o argumento e2 é o terceiro elemento da lista,
 * novamente o retorno desta execução entra como argumento e1 e o argumento e2 será o quarto elemento da lista.
 * E continua até que a lista acabe.
 * Utilizar apenas funções associativas (independente da ordem o resultado permanece).
 * Utilizado para trabalhar com objetos IMULTAVEIS.
 */
public class MainReduce {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        Optional<Integer> reduce = list.stream()
                .reduce((e1, e2) -> e1 + e2); //ou Integer::sum
        reduce.ifPresent(System.out::println);

        //Não precisa retornar um optional, porque ele já tem um retorno garantido: 0.
        //Nesta situação o argumento e1 recebe o 'identity' e o e2 o primeiro elemento da lista, depois segue como o reguce anterior.
        Integer reduceComIdentity = list.stream()
                .reduce(0, Integer::sum);
        reduce.ifPresent(System.out::println);

        //Utiliza a primeira função lambda para somar alguns grupos, exemplo: grupo1=(e1 + e2) grupo2=(e3 + e4) grupo3=(e5 +e6)
        //Depois utiliza a segunda função lambda para combinar os grupos, exemplo: (grupo1+grupo2)=retorno
        // continua usando a segunda função para combinar, exemplo: (resultado+grupo3)
        //O exemplo aqui não faz sentido porque a função de acumulação é a mesma da combinação/assossiativa.
        //Geralmente este caso é utilizado para possibilitar um ganho de performance.
        Integer reduceComCombiner = list.stream()
                .reduce(0,
                        Integer::sum,  // primeira função lambda
                        Integer::sum); // segunda função lambda
        reduce.ifPresent(System.out::println);


    }
}
