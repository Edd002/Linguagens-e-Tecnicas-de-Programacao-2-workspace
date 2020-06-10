package exercicioDigitos;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Validacao validacao = new Validacao();
		Digitos digitos = new Digitos();

		// Informar e validar dígitos
		do {
			System.out.println("Informe o código (11 dígitos numéricos): ");

			if (validacao.validarDigitos(digitos, scanner.nextLine()) == true)
				break;

			System.err.println("O código deve conter exatamente 11 caracteres numéricos.\n");
			TimeUnit.MILLISECONDS.sleep(5);
		} while (true);


		// Armazenar dígitos verificadores convertendo para inteiro
		digitos.setPrimeiroDigitoVerificador(Character.digit(digitos.getCodigoCaracter().charAt(digitos.getCodigoCaracter().length() - 2), 10));
		digitos.setSegundoDigitoVerificador(Character.digit(digitos.getCodigoCaracter().charAt(digitos.getCodigoCaracter().length() - 1), 10));


		// Percorrer os nove dígitos para obter os dois dígitos verificadores
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
			System.out.println("\nDígito Correto");
		else
			System.out.println("\nDígito Inválido");


		System.out.println("\n\nFim do programa.");
		scanner.close();
	}
}