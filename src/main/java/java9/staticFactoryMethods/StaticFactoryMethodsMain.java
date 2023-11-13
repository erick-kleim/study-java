package java9.staticFactoryMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import java8.optional.MainOptional;

/*
 * Métodos estáticos que criam objetos.
*/
public class StaticFactoryMethodsMain {
	
	public static void main(String[] args) {
		//Optional<Integer> antigo = new Optional(100); // Apenas um exemplo de como seria anteriormente.
		
		//Usando da forma a baixo, fica claro o que o método fará:
		Optional<Integer> empty = Optional.empty(); //retorna um Optional Empty.
		Optional<Integer> OptCem = Optional.of(100); //retorna um Optional com o objeto informado.
		Optional<Integer> notNull = Optional.ofNullable(100); //retorna um Optional com o objeto informado e não nulo.
		
		//Retorno de tipo flexivel
		ArrayList<Object> list = new ArrayList<>();
		List<Object> unmodifiableList = Collections.unmodifiableList(list); //cria um alista que não pode ser modificada.
		
		//Controle das instâncias
		Integer cem = Integer.valueOf(2); //Dentro deste método olha para a cache, antes de criar um novo Integer.
		Optional<Integer> cacheEmpty = Optional.empty(); //Retorna sempre o mesmo EMPTY, um static final.
		
		List<Integer> of = List.of();
		List<Integer> of1 = List.of(1);
		List<Integer> of2 = List.of(1,2); //... Continua até 10 elementos, depois disso utiliza .of(E... elements)
		//Map e Set também ganharam estes métodos.
		Map<Object, Object> mapOf = Map.of();
		Set<Object> setOf = Set.of();
		
	}
}
