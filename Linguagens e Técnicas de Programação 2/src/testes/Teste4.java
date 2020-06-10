package testes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero = 0;

		do {
			try {
				System.out.println("Informe o número inteiro: ");
				numero = scanner.nextInt();
				break;
			} catch (InputMismatchException inputMismatchException) {
				System.out.println("Número inválido.");
				scanner.nextLine();
			} catch (NumberFormatException numberFormatException) {
				System.out.println("Não foi possível realizar a conversão.");
				scanner.nextLine();
			} catch (Exception exception) {
				System.out.println("Erro não mapeado. Causa: " + exception.getCause());
				scanner.nextLine();
			}
		} while (true);

		System.out.println("Número: " + numero);
		System.out.println("--Fim do Teste--");
		scanner.close();
	}
}