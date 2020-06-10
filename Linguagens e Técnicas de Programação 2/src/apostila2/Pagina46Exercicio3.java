package apostila2;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pagina46Exercicio3 {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);

		receberDados(scanner);

		System.out.println("\n\nFim do programa.");
	}

	private static void receberDados(Scanner scanner) throws InterruptedException {
		String nomesHospedes[] = new String[100];
		String dataEntrada = null;
		String dataSaida = null;
		String tipoQuarto = null;
		String flag = null;
		int contadorHospedes = 0;
		double contasHospedes[] = new double[100];

		do {
			System.out.println("Informe o nome do hóspede: ");
			nomesHospedes[contadorHospedes] = scanner.nextLine();
	
			do {
				System.out.println("Informe a data de entrada (dd/mm/aaaa): ");
				dataEntrada = scanner.nextLine();
	
				if (validarData(dataEntrada, null))
					break;
			} while (true);
	
			do {
				System.out.println("Informe a data de saída (dd/mm/aaaa): ");
				dataSaida = scanner.nextLine();
	
				if (validarData(dataSaida, dataEntrada))
					break;
			} while (true);
	
			do {
				System.out.println("Informe o tipo do quarto (STANDARD, LUXO OU SUPER-LUXO): ");
				tipoQuarto = scanner.nextLine();
	
				if (validarTipoQuarto(tipoQuarto))
					break;
			} while (true);

			contasHospedes[contadorHospedes] = calcularConta(tipoQuarto, dataEntrada, dataSaida);
			contadorHospedes++;

			System.out.println("\nInforme SAIR para terminar o programa ou qualquer entrada para continuar: ");
			flag = scanner.nextLine();

			System.out.println("\n");
		} while (!flag.toUpperCase().equals("SAIR") && contadorHospedes <= 100);

		exibirRelatorio(nomesHospedes, contasHospedes, contadorHospedes);
	}

	private static boolean validarData(String data, String dataAnterior) throws InterruptedException {
		int dia = 0;
		int mes = 0;
		int ano = 0;

		if (data.length() != 10) {
			exibirErro("A data não está no tamanho correto.\n");
			return false;
		}

		if (data.charAt(2) != '/' || data.charAt(5) != '/') {
			exibirErro("A data não está no formato correto.\n");
			return false;
		}

		try {
			Long.parseLong(data.substring(0,2) + data.substring(3,5) + data.substring(6));
		} catch (Exception exception) {
			exibirErro("A data deve conter apenas números separados por barras.\n");
			return false;
		}

		dia = Integer.parseInt(data.substring(0,2));
		mes = Integer.parseInt(data.substring(3,5));
		ano = Integer.parseInt(data.substring(6));

		if (mes < 1 || mes > 12) {
			exibirErro("O mês deve ser de 01 a 12.\n");
			return false;
		}

		if (ano > 2013) {
			exibirErro("O ano deve ser menor ou igual a 2013.\n");
			return false;
		}

		if (mes % 2 != 0 || mes == 8 || mes == 10 || mes == 12) {
			if (dia < 1 || dia > 31) {
				exibirErro("O dia do mês informado deve estar entre 01 e 31.\n");
				return false;
			}
		}

		if (mes % 2 == 0 || mes == 9 || mes == 11) {
			if (mes == 2) {
				if (dia < 1 || dia > 28) {
					exibirErro("O dia do mês informado deve estar entre 01 e 28.\n");
					return false;
				}
			}
			if (dia < 1 || dia > 30) {
				exibirErro("O dia do mês informado deve estar entre 01 e 30.\n");
				return false;
			}
		}

		if (dataAnterior != null) {
			int diaDataEntrada = Integer.parseInt(dataAnterior.substring(0,2));
			int mesDataEntrada = Integer.parseInt(dataAnterior.substring(3,5));
			int anoDataEntrada = Integer.parseInt(dataAnterior.substring(6));

			if (anoDataEntrada == ano) {
				if (mesDataEntrada == mes) {
					if (diaDataEntrada > dia) {
						exibirErro("O dia de entrada deve ser posterior ao igual ao dia de saída.\n");
						return false;
					}
				} else {
					exibirErro("O mês da data de saída deve ser igual ao mês da data de entrada.\n");
					return false;
				}
			} else {
				exibirErro("O ano da data de saída deve ser igual ao ano da data de entrada.\n");
				return false;
			}
		}

		return true;
	}

	private static boolean validarTipoQuarto(String tipoQuarto) throws InterruptedException {
		if (!tipoQuarto.toUpperCase().equals("STANDARD") && !tipoQuarto.toUpperCase().equals("LUXO") && !tipoQuarto.toUpperCase().equals("SUPER-LUXO")) {
			exibirErro("Entrada para tipo de quarto inválida.\n");
			return false;
		}

		return true;
	}

	private static double calcularConta(String tipoQuarto, String dataEntrada, String dataSaida) {
		int diaEntrada = Integer.parseInt(dataEntrada.substring(0,2));
		int diaSaida = Integer.parseInt(dataSaida.substring(0,2));
		int diasHospedados = diaSaida - diaEntrada;
		double valorDiaria = 0.0;

		if (diasHospedados == 0)
			diasHospedados++;

		if (tipoQuarto.toUpperCase().equals("STANDARD"))
			valorDiaria = 120;
		else if (tipoQuarto.toUpperCase().equals("LUXO"))
			valorDiaria = 150;
		else
			valorDiaria = 180;
		
		return diasHospedados * valorDiaria;
	}

	private static void exibirErro(String erro) throws InterruptedException {
		System.err.println(erro);
		TimeUnit.MILLISECONDS.sleep(5);
	}

	private static void exibirRelatorio(String[] nomesHospdes, double[] contasHospedes, int quantidadeHospedes) {
		double somatorioContas = 0;
		double mediaContas = 0;

		System.out.println("TODAS AS CONTAS");
		System.out.println("Nome do Hóspede \t Valor da Conta");
		for (int i = 0; i < quantidadeHospedes; i++) {
			System.out.printf(nomesHospdes[i] + "\t\t\t\t %.2f", contasHospedes[i]);
			System.out.println();

			somatorioContas += contasHospedes[i];
		}
		mediaContas = somatorioContas / quantidadeHospedes;

		System.out.println("\n\nCONTAS ACIMA DA MÉDIA");
		System.out.println("Nome do Hóspede \t Valor da Conta");
		for (int i = 0; i < quantidadeHospedes; i++) {
			if (contasHospedes[i] > mediaContas) {
				System.out.printf(nomesHospdes[i] + "\t\t\t\t %.2f", contasHospedes[i]);
				System.out.println();
			}
		}
	}
}