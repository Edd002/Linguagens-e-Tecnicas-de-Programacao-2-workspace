package apostila2;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pagina46Exercicio2 {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		String[] nomeEmpregado = new String[100];
		String[] codigoEmpregado = new String[100];
		int[] numeroPecasFabricadas = new int [100];
		int contadorEmpregados = 0;
		char flag = '\u0000';

		do {
			nomeEmpregado[contadorEmpregados] = receberNomeEmpregado();
			codigoEmpregado[contadorEmpregados] = receberCodigoEmpregado(nomeEmpregado[contadorEmpregados]);
			numeroPecasFabricadas[contadorEmpregados] = receberNumeroPecasFabricadas();

			contadorEmpregados++;

			System.out.println("Informe S para sair ou qualquer outra entrada para continuar.\n");
			flag = scanner.next().charAt(0);

			scanner.nextLine();
			System.out.println("\n");
		} while (Character.toUpperCase(flag) != 'S' && contadorEmpregados <= 100);

		gerarRelatorioSalarios(nomeEmpregado, codigoEmpregado, numeroPecasFabricadas, contadorEmpregados);

		System.out.println("\n\nFim do programa.");
	}

	private static String receberNomeEmpregado() throws InterruptedException {
		String nomeEmpregado = null;

		do {
			System.out.println("Informe o nome do empregado: ");
			nomeEmpregado = scanner.nextLine();

			if (nomeEmpregado.length() < 2)
				exibirErro("O nome do empregado deve conter no mínimo 2 caracteres.\n");
		} while (nomeEmpregado.length() < 2);

		return nomeEmpregado;
	}

	private static String receberCodigoEmpregado(String nomeEmpregado) throws InterruptedException {
		String codigoEmpregado = null;
		boolean validarCodigoTamanho = false;
		boolean validarCodigoDoisPrimeirosCaracteres = false;
		boolean validarCodigoTresUltimosCaracteres = false;

		do {
			System.out.println("Informe o código do empregado: ");
			codigoEmpregado = scanner.nextLine();

			validarCodigoTamanho = codigoEmpregado.length() == 5;
			if (!validarCodigoTamanho) {
				exibirErro("O código deve conter 5 caracteres.\n");
			} else {
				validarCodigoDoisPrimeirosCaracteres = codigoEmpregado.substring(0, 2).toUpperCase().equals(nomeEmpregado.substring(0, 2).toUpperCase());
				if (!validarCodigoDoisPrimeirosCaracteres) {
					exibirErro("Os dois primeiros caracteres do código devem ser iguais às duas primeira letras do nome.\n");
				} else {
					try {
						Integer.parseInt(codigoEmpregado.substring(3));
						validarCodigoTresUltimosCaracteres = true;
					} catch (Exception exception) {
						validarCodigoTresUltimosCaracteres = false;
					}
					if (!validarCodigoTresUltimosCaracteres)
						exibirErro("Os três últimos caracteres do código devem ser dígitos inteiros.\n");
				}
			}
		} while (!validarCodigoTamanho || !validarCodigoDoisPrimeirosCaracteres || !validarCodigoTresUltimosCaracteres);

		return codigoEmpregado;
	}

	private static int receberNumeroPecasFabricadas() throws InterruptedException {
		int numeroPecasFabricadas = 0;

		do {
			try {
				System.out.println("Informe o número de peças fábricadas: ");
				numeroPecasFabricadas = scanner.nextInt();

				if (numeroPecasFabricadas <= 0)
					exibirErro("O número de peças fábricadas deve ser maior do que zero.\n");
			} catch (Exception exception) {
				exibirErro("O número de peças fábricadas deve ser um número inteiro.\n");
				scanner.nextLine();
			}
		} while (numeroPecasFabricadas <= 0); 

		return numeroPecasFabricadas;
	}

	private static void gerarRelatorioSalarios(String[] nomeEmpregado, String[] codigoEmpregado, int numeroPecasFabricadas[], int contadorEmpregados) {
		double salario[] = new double[contadorEmpregados]; 
		double somatorioSalarios = 0.0;
		double mediaSalarios = 0.0;

		for (int i = 0; i < contadorEmpregados; i++) {
			if (numeroPecasFabricadas[i] >= 1 && numeroPecasFabricadas[i] <= 200)
				salario[i] = (double) numeroPecasFabricadas[i] * 2.00;
			else if (numeroPecasFabricadas[i] > 200 && numeroPecasFabricadas[i] <= 400)
				salario[i] = (double) numeroPecasFabricadas[i] * 2.30;
			else
				salario[i] = (double) numeroPecasFabricadas[i] * 2.50;

			somatorioSalarios += salario[i];
		}

		System.out.print("Código do Empregado\t");
		System.out.print("Nome do Empregado\t");
		System.out.print("Salário do Empregado");

		for (int i = 0; i < contadorEmpregados; i++) {
			System.out.format("\n" + codigoEmpregado[i] + "\t\t\t" + nomeEmpregado[i] + "\t\t\t" + "R$%.2f", salario[i]);
		}

		mediaSalarios = (double) somatorioSalarios / contadorEmpregados;

		System.out.format("\n\n\nTotal pago com salários: R$%.2f", somatorioSalarios);
		System.out.format("\nMédia salarial: R$%.2f", mediaSalarios);
	}

	private static void exibirErro(String erro) throws InterruptedException {
		System.err.println(erro);
		TimeUnit.MILLISECONDS.sleep(5);
	}
}