package java8.interfacesStatics;

public interface OneInterface {
	static String duplicate(String simple) {
		return simple+simple;
	}
	
	/*
	 * Métodos defaul: evita a necessidade de implementar o método em todas as
	 * classes que implementam esta interface.
	 */
	default String triplicate(String simple) { 
		return simple+simple+simple;
	}
}
