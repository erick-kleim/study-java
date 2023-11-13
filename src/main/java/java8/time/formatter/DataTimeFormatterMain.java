package java8.time.formatter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DataTimeFormatterMain {
	public static void main(String[] args) {
		DateTimeFormatter formatter;
		String formatted; 
		
		LocalDateTime local = LocalDateTime.now();
		System.out.println(local);
		
		/*
		 * ISO e RFC são constantes e geralmente são usadas na transferencia de dados
		 * entre sistemas e não para mostrar ao usuário final. ISO_DATE_TIME é a
		 * formatação padrão
		 */
		System.out.println("\n ISO e RFC");
		formatter = DateTimeFormatter.ISO_DATE_TIME;
		formatted = formatter.format(local);
		System.out.println(formatted); // apesar de formatoado com ISO_DATE_TIME não alterou a exibição.
		
		formatter = DateTimeFormatter.ISO_WEEK_DATE; //... ou outro exemplo, existem vários.
		formatted = formatter.format(local);
		System.out.println(formatted); 
		
		/*
		 * FormnatStyle
		 *  
		 */

		System.out.println("\n DateTimeFormatter.ofLocalizedDate");
		formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		formatted = formatter.format(local);
		System.out.println("FormatStyle.SHORT: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		formatted = formatter.format(local);
		System.out.println("FormatStyle.MEDIUM: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
		formatted = formatter.format(local);
		System.out.println("FormatStyle.LONG: " + formatted);
		

		System.out.println("\n DateTimeFormatter.ofLocalizedDateTime");
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		formatted = formatter.format(local);
		System.out.println("FormatStyle.SHORT: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		ZonedDateTime zoned = ZonedDateTime.now(); //para esta formatação é utilizado o zoneID para formatação, por isso alterado o tipo.
		formatted = formatter.format(zoned);
		System.out.println("FormatStyle.LONG: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatted = formatter.format(zoned);
		System.out.println("FormatStyle.LONG: " + formatted);
		
		System.out.println("\n Locale");
		Locale.setDefault(Locale.ITALY);//altera para como é utilizado por padrão os formatos das datas neste local.
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL); //idioma da frase em italiano.
		formatted = formatter.format(ZonedDateTime.now());
		System.out.println("Locale.ITALY: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT); // aqui mostra a a hora no formato 24H.
		formatted = formatter.format(ZonedDateTime.now());
		System.out.println("Locale.ITALY: " + formatted);
		Locale.setDefault(Locale.US);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatted = formatter.format(zoned);
		System.out.println("Locale.UK: " + formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT); // aqui mostra a a hora no formato 12H+(PM/AM).
		formatted = formatter.format(zoned);
		System.out.println("Locale.UK: " + formatted);
		Locale.setDefault(Locale.ROOT); //voltando para o local de onde o systema esta sendo executado.
		System.out.println("Locale.ROOT: " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zoned));
		
		System.out.println("\n DateTimeFormatter.ofPattern");
		formatter = DateTimeFormatter.ofPattern("dd");
		formatted = formatter.format(local);
		System.out.println("dd: " + formatted);
		formatter = DateTimeFormatter.ofPattern("MM-yyyy");
		formatted = formatter.format(local);
		System.out.println("MM-yyyy: " + formatted);
		
		System.out.println("\n Método \".format\" também é implementado pelas classes da datas."
				+ "\nPortanto se inverter a chamado o resultado é o mesmo?");
		String formatted1 = local.format(formatter);
		String formatted2 = formatter.format(local);
		System.out.println("R: " + formatted1.equals(formatted2));
		
		System.out.println("\n Parse");
		formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
		TemporalAccessor parse = formatter.parse("12_05_1990_18_30_42");
		LocalDateTime from = LocalDateTime.from(parse);
		System.out.println("from: " + from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		
	}
}