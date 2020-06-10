package exercicioCPF;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CPF {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);

		String CPF = null;
		String mensagemValidacaoCPF = null;
		String novoCPF = null;

		do {
			System.out.println("Informe o CPF: ");
			CPF = scanner.nextLine();

			mensagemValidacaoCPF = validarCPF(CPF);

			if (mensagemValidacaoCPF != null) {
				// Colocar vermelho com o time
				System.err.println(mensagemValidacaoCPF + "\n");
				TimeUnit.MILLISECONDS.sleep(5);
			}

		} while (mensagemValidacaoCPF != null);

		novoCPF = CPF.substring(0, CPF.length() - 2) + calcularDigitoVerificador(CPF, 11, 9);
		novoCPF += calcularDigitoVerificador(novoCPF, 12, 10);

		System.out.println("CPF informado: " + formatarCPF(CPF));
		System.out.println("CPF validado: " + formatarCPF(novoCPF));

		System.out.println("\n\nFim do Programa.");
		scanner.close();
	}

	// MÉTODOS
	/*
	 * -------------------- Validação: diferente de 11 digitos, não numérico e
	 * igualdade entre todos digitos --------------------
	 */
	private static String validarCPF(String CPF) {
		if (!validarTamanhoCPF(CPF))
			return "O CPF deve conter exatamente 11 dígitos";
		else if (!validarSomenteNumerosCPF(CPF))
			return "O CPF deve conter apenas dígitos numéricos.";
		else if (!validarSomenteUmTipoDigitoCPF(CPF))
			return "O CPF não pode conter todos os dígitos iguais.";

		return null;
	}

	private static boolean validarTamanhoCPF(String CPF) {
		if (CPF.length() != 11)
			return false;

		return true;
	}

	private static boolean validarSomenteNumerosCPF(String CPF) {
		try {
			Long.parseLong(CPF);
		} catch (Exception exception) {
			return false;
		}

		return true;
	}

	private static boolean validarSomenteUmTipoDigitoCPF(String CPF) {
		for (int i1 = 0; i1 < CPF.length(); i1++) {
			char caractere1 = CPF.charAt(i1);

			for (int i2 = 0; i2 < CPF.length(); i2++) {
				char caractere2 = CPF.charAt(i2);

				if (i1 != i2) {
					if (caractere1 != caractere2)
						return true;
				}
			}
		}

		return false;
	}
	/*----------------------------------------------------------------------------------------------------------------------------*/

	// Cálculo dígito verificador
	private static int calcularDigitoVerificador(String CPF, int peso, int colunas) {
		int digitos[][] = new int[3][colunas];
		int somatorioMultiplicacoes = 0;
		int resultadoDivisaoRestoDigitoVerificador = 0;
		int digitoVerificador = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < colunas; j++) {
				if (i == 0) {
					digitos[i][j] = Character.digit(CPF.charAt(j), 10);
				} else if (i == 1) {
					digitos[i][j] = --peso;
				} else {
					digitos[i][j] = digitos[i - 2][j] * digitos[i - 1][j];
					somatorioMultiplicacoes += digitos[i][j];
				}
			}
		}

		resultadoDivisaoRestoDigitoVerificador = somatorioMultiplicacoes % 11;

		if (resultadoDivisaoRestoDigitoVerificador >= 2)
			digitoVerificador = 11 - resultadoDivisaoRestoDigitoVerificador;

		return digitoVerificador;
	}

	// Colocar o CPF na máscara padrão
	private static String formatarCPF(String CPF) {
		return CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9);
	}
}