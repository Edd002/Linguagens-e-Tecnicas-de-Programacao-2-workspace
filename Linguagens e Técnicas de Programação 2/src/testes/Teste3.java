package testes;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Teste3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// CONVERSÕES
		double value = 200.3456;

		// Primeira forma
		System.out.printf("Value: %.2f", value);

		System.out.println();
		System.out.println();

		// Segunda forma
		String result = String.format("%.2f", value);
		System.out.println("Value: " + result);

		System.out.println();

		// Terceira forma
		DecimalFormat df = new DecimalFormat("####0.00");
		System.out.println("Value: " + df.format(value));

		System.out.println("\n\nFim dos testes.");

		scanner.close();
	}
}