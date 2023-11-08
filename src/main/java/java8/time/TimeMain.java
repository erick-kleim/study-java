package java8.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

//Não conseguimos chamar o construtor destas classes, para instanciá-las podemos usar as variações dos métodos:
// .now();
//.of(); Atentar que cada classe tem algumas variações nos argumentos.
public class TimeMain {
    public static void main(String[] args) {
        System.out.println("\n LocalDate representa uma data");
        LocalDate localDate = LocalDate.now();
        System.out.println("now: "+localDate);
        LocalDate localDateOf = LocalDate.of(2023, Month.OCTOBER, 30); //Enum Month adicionado nesta versão
        System.out.println("of: "+ localDateOf);

        System.out.println("\n LocalTime representa um horário");
        LocalTime localTime = LocalTime.now();
        System.out.println("now: "+localTime);
        LocalTime localTimeOf = LocalTime.of(12, 54);
        System.out.println("of: "+ localTimeOf);

        System.out.println("\n LocalDateTime representa uma data com horario");
        LocalDateTime localDateTime =  LocalDateTime.now();
        System.out.println("now: "+localDateTime);
        System.out.println("of: "+LocalDateTime.of(localDateOf, localTimeOf));

        System.out.println("\n Instant representa uma instante ou momento no tempo, medido em milissegundos," +
                " contando a partir de 01/01/1970 00:00 do GMT, ou seja conta a partir do timezone 0.");
        Instant instant =  Instant.now();
        System.out.println("now: "+instant);
        System.out.println("of: "+Instant.ofEpochMilli(990000000000L));

        System.out.println("\n ZonedDateTime é um LocalDateTime com TimeZone");
        ZonedDateTime zonedDateTime =  ZonedDateTime.now();
        System.out.println("now: "+zonedDateTime);
        ZoneId zone = ZoneId.of("Asia/Tokyo");
        System.out.println("of: "+ZonedDateTime.of(localDateOf, localTimeOf, zone));


        System.out.println("\n Manipulações (imultaveis)");
        System.out.println("Antes: " + localDate);
        localDate.plusDays(10);
        localDate.plusMonths(5);
        localDate.plusWeeks(3);
        localDate.plusYears(2);
        localDate.minusDays(1);
        localDate.minusMonths(1);
        /* ... Continuam os métodos de manupulação tanto para o LocalDate quanto para as outras classes da java.time.
        *  Ex:
        *      localTime.plusHours(2);
        *      localTime.plusMinutes(100);
        *      localTime.plusSeconds(60);
        *      localTime.minusHours(4);
        *       (...)
        * */
        System.out.println("Depois: "+ localDate); //não alterou o valor da variavel, o valor alterado é retornado pelo metodo.
        localDate = localDate.plusDays(1);
        localDate = localDate.plusMonths(1);
        System.out.println("Depois da atribuição: "+ localDate); //agora o valor foi pego.

        localDate = localDate.plusDays(1).minusMonths(3).minusWeeks(3).plusYears(1);
        System.out.println("Depois da manipulação encadeada: "+ localDate);

        ChronoUnit chronoUnit = ChronoUnit.DAYS;
        localDate = localDate.plus(2, chronoUnit); //uma outra forma de manipular as datas e mais flexivel.
        System.out.println("Depois da manipulação encadeada: "+ localDate);

        System.out.println("\n Erros em tempo de execução (DateTimeException)");
        try {
            LocalDate.of(1999, 12, -1);
        }catch (DateTimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n Period representa um periodo de tempo em Dias," +
                " inicia com P(indica que é um periodo) Y(anos)M(Meses)D(dias)");
        Period of = Period.of(1,2,3);
        System.out.println("of: " + of);

        Period between = Period.between(localDateOf, localDate);
        System.out.println("Between: "+ between);
        System.out.println("Between invertido: "+ Period.between(localDate, localDateOf));

        Period period = between.plusYears(2);
        System.out.println("plus: "+ period);

        Period until = localDate.until(localDateOf);
        System.out.println("until: "+ until);
        System.out.println("until invertido: "+ localDateOf.until(localDate));

        LocalDate plusPeriod = localDate.plus(period);
        System.out.println("LocalDate somado a um Period: "+ plusPeriod);

        System.out.println("\n Duration representa uma duração de tempo em Horas" +
                " inicia com PT(indica que é uma Duration) H(horas)M(minutos)S(segundos)," +
                " os milissegundos também estão presentes nas casas decimais dos segundos.");
        Duration durationDays = Duration.ofDays(2);
        System.out.println("2 Days:" + durationDays);

        Duration horas = Duration.ofHours(14);
        System.out.println("ofHours: "+ horas);

        Duration segundosNanosegundos = Duration.ofSeconds(42, 1);
        System.out.println("Segundos com milissegundos: "+ segundosNanosegundos);

        Duration durationBetween = Duration.between(localTimeOf, localTime);
        System.out.println("between: " + durationBetween);

        Duration plusMillis = durationBetween.plusMillis(5000);
        System.out.println("plus: " + plusMillis);

        LocalTime plusDuration = LocalTime.of(2,30).plus(Duration.ofHours(10));
        System.out.println("LocalTime somado a um Duraton: " + plusDuration);


    }
}
