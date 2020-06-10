package testes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero = 0;

		do {
			try {
				System.out.println("Informe o n�mero inteiro: ");
				numero = scanner.nextInt();
				break;
			} catch (InputMismatchException inputMismatchException) {
				System.out.println("N�mero inv�lido.");
				scanner.nextLine();
			} catch (NumberFormatException numberFormatException) {
				System.out.println("N�o foi poss�vel realizar a convers�o.");
				scanner.nextLine();
			} catch (Exception exception) {
				System.out.println("Erro n�o mapeado. Causa: " + exception.getCause());
				scanner.nextLine();
			}
		} while (true);

		System.out.println("N�mero: " + numero);
		System.out.println("--Fim do Teste--");
		scanner.close();
	}
}