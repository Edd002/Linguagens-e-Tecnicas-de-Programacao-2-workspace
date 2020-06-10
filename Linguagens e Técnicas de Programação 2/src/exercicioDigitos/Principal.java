package exercicioDigitos;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Validacao validacao = new Validacao();
		Digitos digitos = new Digitos();

		// Informar e validar d�gitos
		do {
			System.out.println("Informe o c�digo (11 d�gitos num�ricos): ");

			if (validacao.validarDigitos(digitos, scanner.nextLine()) == true)
				break;

			System.err.println("O c�digo deve conter exatamente 11 caracteres num�ricos.\n");
			TimeUnit.MILLISECONDS.sleep(5);
		} while (true);


		// Armazenar d�gitos verificadores convertendo para inteiro
		digitos.setPrimeiroDigitoVerificador(Character.digit(digitos.getCodigoCaracter().charAt(digitos.getCodigoCaracter().length() - 2), 10));
		digitos.setSegundoDigitoVerificador(Character.digit(digitos.getCodigoCaracter().charAt(digitos.getCodigoCaracter().length() - 1), 10));


		// Percorrer os nove d�gitos para obter os dois d�gitos verificadores
		int resultadoPrimeiroDigitoVerificador;
		int resultadoSegundoDigitoVerificador;
		int somaValoresCodigo = 0;
		int produtoValoresCodigo = 1;

		for (int i = 0; i < digitos.getCodigoCaracter().length() - 2; i++) {
			somaValoresCodigo += Character.digit(digitos.getCodigoCaracter().charAt(i), 10);
			produtoValoresCodigo *= Character.digit(digitos.getCodigoCaracter().charAt(i), 10);
		}

		resultadoPrimeiroDigitoVerificador = Character.digit(Integer.toString(somaValoresCodigo / 10).charAt(0), 10);
		resultadoSegundoDigitoVerificador = Character.digit(String.valueOf(produtoValoresCodigo).charAt(String.valueOf(produtoValoresCodigo).length() - 1), 10);
		// resultadoSegundoDigitoVerificador = produtoValoresCodigo % 10;

		// Verificar o resultado
		if (resultadoPrimeiroDigitoVerificador == digitos.getPrimeiroDigitoVerificador() && resultadoSegundoDigitoVerificador == digitos.getSegundoDigitoVerificador())
			System.out.println("\nD�gito Correto");
		else
			System.out.println("\nD�gito Inv�lido");


		System.out.println("\n\nFim do programa.");
		scanner.close();
	}
}