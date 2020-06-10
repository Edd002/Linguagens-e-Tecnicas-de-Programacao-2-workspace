package apostila2;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pagina45Exercicio1 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		receberNome();

		System.out.println("\n\nFim do programa.");
	}

	private static void receberNome() throws InterruptedException {
		String nome = null;

		do {
			System.out.println("Informe o nome: ");
			nome = scanner.nextLine();

			if (nome.equals(""))
				exibirErro("O nome deve ser informado.\n");
		} while (nome.equals(""));

		nome = eliminarEspacosBrancoEsquerdaNome(nome);
		System.out.println("Nome sem espa�os em branco no in�cio: " + nome);

		nome = eliminarEspacosBrancoDireitaNome(nome);
		System.out.println("Nome sem espa�os em branco no final: " + nome);

		nome = eliminarEspacosBrancosExcessoMeioNome(nome);
		System.out.println("Nome sem excesso de espa�o entre as palavras: " + nome);

		System.out.println("Convers�o da primeira letra para mai�sculo: " + converterPrimeiraLetraNomeMaiusculo(nome));
	}

	private static String eliminarEspacosBrancoEsquerdaNome(String nome) {
		int contadorVerificador = 0;
		while (nome.charAt(0) == ' ') {
			nome = nome.substring(contadorVerificador);
			contadorVerificador++;
		}

		return nome;
	}

	private static String eliminarEspacosBrancoDireitaNome(String nome) {
		int contadorVerificador = nome.length() - 1;
		while (nome.charAt(nome.length() - 1) == ' ') {
			nome = nome.substring(0, contadorVerificador);
			contadorVerificador--;
		}

		return nome;
	}

	private static String eliminarEspacosBrancosExcessoMeioNome(String nome) {
		// trim(): reitra espa�os no in�cio e no fim
		// replaceAll(" +", " "): substitui qualquer conjunto de espa�os por uma espa�o vazio
		return nome.trim().replaceAll(" +", " ");
	}

	private static String converterPrimeiraLetraNomeMaiusculo(String nome) {
		return nome.substring(0, 1).toUpperCase() + nome.substring(1);
	}

	private static void exibirErro(String erro) throws InterruptedException {
		System.err.println(erro);
		TimeUnit.MILLISECONDS.sleep(5);
	}
}