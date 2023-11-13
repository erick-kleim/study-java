package java9.jshell;

//REPL = Read Evbaluete Print Loop
public class JShellMain {

	public static void main(String[] args) {
		System.out.println("""
				Para utilizar o JShell, abra um terminal e execute o comando:
				\tjshell
				ou
				\tjshell -v

				Para sair execute o comando:
				\t/exit
				
				A tecla TAB é de grande valia dentro do JShell, não apenas para completar o código,
				mas também para completar comandos e pegar a documentação das classes e métodos.
				
				Variáveis podem ser declaradas ex:
				int x (não precisa de ; e o valor default do int é 0)
				String y (não precisa de ; e o valor default da String é null)
				int x = 99 (também pode ser criada com valor)
				
				Métodos podem ser criados, ao colocar a { no final da linha e pressionar enter, o JShell 
				entende que é um método sendo escrito e permite escrever múltiplas linhas até que feche com a }
				
				O comando abaixo mostra dados os comandos executados pelo usuário do JShell
				\t/list
				O comando abaixo mostra todos os comandos executados tanto pelo usuário do JShell,
				quanto os executados em segundo plano pelo próprio JShell.
				\t/list -all
				Quando uma Exception for lançada e indicar a linha do erro, o número da linha é baseada 
				nesta lista de comandos impressa pelo /list -all. 
				
				SHIFT + TAB + i = auxilia no import.
				SHIFT + TAB + v = auxilia a criar variáveis.
				""");
	}
}
