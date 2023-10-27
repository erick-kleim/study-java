package java8.optional;

import java.util.Optional;
import java.util.OptionalInt;

public class MainOptional {
    public static void main(String[] args) {
        String number = "12";
        System.out.println(toInteger(number).get());

        String notNumber = "Exception";
        Optional<Integer> optionalEmpty = toInteger(notNumber);
        try {
            System.out.println(optionalEmpty.get());
        }catch (Exception e){
            System.out.println("Exception message: " + e.getMessage());
        }

        //Tenta converter para inteiro, consegue e mostra inteiro no console (executa a função lambda).
        toInteger(number).ifPresent(e->System.out.println(e)); //ou System.out::println

        //Tenta converter para inteiro, gera exeption, retorna Optiona empty e por isso não mostra nada (não executa a função lambda).
        toInteger(notNumber).ifPresent(e->System.out.println(e)); //ou System.out::println

        //Tenta converter para inteiro, gera exeption, retorna Optiona empty e por isso o orElse retorna o valor default 12000.
        Integer orElse = toInteger(notNumber).orElse(12000);
        System.out.println(orElse);

        //Tenta converter para inteiro, consegue, e por isso o orElse retorna o valor convertido 12 e NÃO o valor default.
        orElse = toInteger(number).orElse(12000); //getDefaultnumber()
        System.out.println(orElse);

        //Tenta converter para inteiro, consegue, e por isso o orElseGet executa uma func. lambda que retorna o valor default.
        orElse = toInteger(notNumber).orElseGet(() -> {
            return getDefaultnumber();
        });
        /*
            Outras maneiras de escrever as linhas acima:
        orElse = toInteger(notNumber).orElseGet(() -> getDefaultnumber());
        orElse = toInteger(notNumber).orElseGet(MainOptional::getDefaultnumber);
         */
        System.out.println(orElse);

        //Tenta converter para inteiro, não consegue, retorna empty e por isso executa o orElseThrow que retorna uma Exception.
        try {
            orElse = toInteger(notNumber).orElseThrow(() -> new NullPointerException("Sem valor."));
        } catch (NullPointerException npe){
            System.out.println("Mensagem inserida no erro: "+npe.getMessage());

        }

        /*
        * Optional com tipos primitivos:
        * uma vantagem é que pode se tratar um tipo primitivo como "null"/empty usando o .empty()
        * Existentes:
        *   OptionalDouble
        *   OptionalInt
        *   OptionalLong
        * */

        //Exemplo do uso do OptionalInt retornando valor do tipo int
        int intValue = toInt(number).orElse(12000);
        System.out.println(intValue);
    }

    private static Integer getDefaultnumber() {
        return 12000;
    }

    private static Optional<Integer> toInteger(final String number){
        try{
        Integer integer = Integer.valueOf(number);
        return Optional.of(integer);
    } catch (Exception e){
            return Optional.empty();
        }
    }

    private static OptionalInt toInt(final String number){
        try{
            int integer = Integer.parseInt(number);
            return OptionalInt.of(integer);
        } catch (Exception e){
            return OptionalInt.empty();
        }
    }
}
