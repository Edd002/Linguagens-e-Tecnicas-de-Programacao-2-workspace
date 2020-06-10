package exercicioPrecoPassagem;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PrecoPassagem {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		gerarPassagens();

		System.out.println("\n\nFim do programa.");
	}

	public static void gerarPassagens() throws InterruptedException {
		String[] nomePassageiro = new String[200];
		int[] quantidadePassagem = new int[200];
		char[] destinoPassageiro = new char[200];
		char[] tipoPassagem = new char[200];
		double valorPassagem[] = new double[200];
		String flag = null;
		int contadorPessoas = 0;

		do {
			nomePassageiro[contadorPessoas] = receberNomePassageiro();
			quantidadePassagem[contadorPessoas] = receberQuantidadePassagem();
			destinoPassageiro[contadorPessoas] = receberDestinoPassageiro();
			tipoPassagem[contadorPessoas] = receberTipoPassagem();
			valorPassagem[contadorPessoas] = calcularValorPassagem(quantidadePassagem[contadorPessoas], destinoPassageiro[contadorPessoas], tipoPassagem[contadorPessoas]);

			exibirResultado(nomePassageiro, quantidadePassagem, destinoPassageiro, tipoPassagem, valorPassagem, contadorPessoas, false);

			contadorPessoas++;

			System.out.println("\nInforme qualquer tecla para continuar ou informe FIM para sair.\n");
			flag = scanner.nextLine();
		} while (!flag.equalsIgnoreCase("FIM") && contadorPessoas < 200);

		exibirResultado(nomePassageiro, quantidadePassagem, destinoPassageiro, tipoPassagem, valorPassagem, contadorPessoas, true);
	}

	public static String receberNomePassageiro() throws InterruptedException {
		String nomePassageiro = null;

		do {
			System.out.println("Informe o nome do passageiro: ");
			nomePassageiro = scanner.nextLine();

			if (nomePassageiro.equals(""))
				exibirErro("O nome do passageiro não pode ser vazio.\n");
		} while (nomePassageiro.equals(""));

		return nomePassageiro;
	}

	public static int receberQuantidadePassagem() throws InterruptedException {
		int quantidadePassagens = 0;
		boolean validador;

		do {
			validador = true;
			try {
				System.out.println("Informe a quantidade de passagens: ");
				quantidadePassagens = scanner.nextInt();

				if (quantidadePassagens <= 0) {
					exibirErro("A quantidade deve ser um número positivo.\n");
					validador = false;
				}
			} catch (Exception exception) {
				exibirErro("A quantidade deve ser um número inteiro.\n");
				validador = false;
			}

			scanner.nextLine();
		} while (!validador);

		return quantidadePassagens;
	}

	public static char receberDestinoPassageiro() throws InterruptedException {
		String destinoPassageiro = null;

		do {
			System.out.println("Informe o destino do passageiro: \n1- Região Norte \n2- Região Nordeste \n3- Região Centro-Oeste \n4- Região Sul");
			destinoPassageiro = scanner.nextLine();

			if (destinoPassageiro.length() != 1 || destinoPassageiro.charAt(0) < 49 || destinoPassageiro.charAt(0) > 52)
				exibirErro("Destino inválido.\n");
		} while (destinoPassageiro.length() != 1 || destinoPassageiro.charAt(0) < 49 || destinoPassageiro.charAt(0) > 52);

		return destinoPassageiro.charAt(0);
	}

	public static char receberTipoPassagem() throws InterruptedException {
		String tipoPassagem = null;

		do {
			System.out.println("Informe tipo da passagem: \n1- Ida \n2- Ida e Volta");
			tipoPassagem = scanner.nextLine();

			if (tipoPassagem.length() != 1 || tipoPassagem.charAt(0) < 49 || tipoPassagem.charAt(0) > 50)
				exibirErro("Tipo inválido.\n");
		} while (tipoPassagem.length() != 1 || tipoPassagem.charAt(0) < 49 || tipoPassagem.charAt(0) > 50);

		return tipoPassagem.charAt(0);
	}

	public static double calcularValorPassagem(int quantidadePassagem, char destinoPassageiro, char tipoPassagem) {
		double desconto = 1.0;

		if (quantidadePassagem > 1)
			desconto = 0.965;

		if (tipoPassagem == '1') {
			if (destinoPassageiro == '1')
				return 500 * quantidadePassagem * desconto;
			else if (destinoPassageiro == '2')
				return 350 * quantidadePassagem * desconto;
			else if (destinoPassageiro == '3')
				return 350 * quantidadePassagem * desconto;
			else
				return 300 * quantidadePassagem * desconto;
		} else {
			if (destinoPassageiro == '1')
				return 900 * quantidadePassagem * desconto;
			else if (destinoPassageiro == '2')
				return 650 * quantidadePassagem * desconto;
			else if (destinoPassageiro == '3')
				return 600 * quantidadePassagem * desconto;
			else
				return 550 * quantidadePassagem * desconto;
		}
	}

	public static void exibirResultado(String[] nomePassageiro, int[] quantidadePassagem, char[] destinoPassageiro, char[] tipoPassagem, double[] valorPassagem, int contadorPessoas, boolean resultadoFinal) {
		if (resultadoFinal) {
			int quantidadePassagemRegiaoSul = 0;
			double somatorioValorPassagem = 0.0;
			double mediaValorPassagem = 0.0;

			for (int i = 0; i < contadorPessoas; i++) {
				System.out.println("--------------------------");
				System.out.println("--------------------------");
				System.out.println("Nome do passageiro: " + nomePassageiro[i]);
				System.out.println("Total de passagens: " + quantidadePassagem[i]);
	
				if (destinoPassageiro[i] == '1') {
					System.out.println("Destino: Região Norte");
				} else if (destinoPassageiro[i] == '2') {
					System.out.println("Destino: Região Nordeste");
				} else if (destinoPassageiro[i] == '3') {
					System.out.println("Destino: Região Centro-Oeste");
				} else {
					System.out.println("Destino: Região Sul");
					quantidadePassagemRegiaoSul++;
				}
	
				if (tipoPassagem[i] == '1')
					System.out.println("Passagem de Ida");
				else
					System.out.println("Passagem de Ida e Volta");

				System.out.printf("Valor total: R$ %.2f \n", valorPassagem[i]);

				somatorioValorPassagem += valorPassagem[i];
			}
			System.out.println("--------------------------");
			System.out.println("--------------------------");
			System.out.println();

			mediaValorPassagem = somatorioValorPassagem / (contadorPessoas);

			System.out.println("Quantidade de passagens vendidas para Região Sul: " + quantidadePassagemRegiaoSul);
			System.out.printf("Média dos valores faturados com passagens: R$ %.2f \n", mediaValorPassagem);
		} else {
			System.out.println("--------------------------");
			System.out.println("--------------------------");
			System.out.println("Nome do passageiro: " + nomePassageiro[contadorPessoas]);
			System.out.println("Total de passagens: " + quantidadePassagem[contadorPessoas]);

			if (destinoPassageiro[contadorPessoas] == '1')
				System.out.println("Destino: Região Norte");
			else if (destinoPassageiro[contadorPessoas] == '2')
				System.out.println("Destino: Região Nordeste");
			else if (destinoPassageiro[contadorPessoas] == '3')
				System.out.println("Destino: Região Centro-Oeste");
			else
				System.out.println("Destino: Região Sul");

			if (tipoPassagem[contadorPessoas] == '1')
				System.out.println("Passagem de Ida");
			else
				System.out.println("Passagem de Ida e Volta");

			System.out.printf("Valor total: R$ %.2f \n", valorPassagem[contadorPessoas]);
			System.out.println("--------------------------");
			System.out.println("--------------------------");
		}
	}

	private static void exibirErro(String erro) throws InterruptedException {
		System.err.println(erro);
		TimeUnit.MILLISECONDS.sleep(5);
	}
}