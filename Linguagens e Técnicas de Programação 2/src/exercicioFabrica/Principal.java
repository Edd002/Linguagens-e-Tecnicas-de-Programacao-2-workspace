package exercicioFabrica;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Fabrica fabrica = new Fabrica();
		Validacao validacao = new Validacao();

		fabrica.setTotalGastoPagamentoSalarios(0);
		fabrica.setNumeroTotalPecasFabricadas(0);
		fabrica.setCodigoOperarioMenorNumeroPecas(0);
		fabrica.setMediaQuantidadeFabricasOperarioB(0);

		int menorNumeroPecas = Integer.MAX_VALUE;
		int pecasFabricadasOperarioB = 0;
		int contadorOperario = 0;

		while (true) {
			do {
				System.out.println("Informe o código do operário: ");
				if (validacao.validarOperarioInteiro(fabrica, scanner.nextLine()) == true) {
					break;
				} else {
					System.err.println("O código do operário deve ser um número inteiro.\n");
					TimeUnit.MILLISECONDS.sleep(5);
				}
			} while (true);
	
			System.out.println();
	
			do {
				System.out.println("Informe a classe do operário: ");
				if (validacao.validarClasseUmCaracterLetraABC(fabrica, scanner.nextLine()) == true) {
					break;
				} else {
					System.err.println("A classe do operário deve conter exatamente uma letra (A, B ou C).\n");
					TimeUnit.MILLISECONDS.sleep(5);
				}
			} while (true);
	
			System.out.println();
	
			do {
				System.out.println("Informe o número de peças fábricadas no mês: ");
				if (validacao.validarPecasInteiro(fabrica, scanner.nextLine()) == true) {
					break;
				} else {
					System.err.println("O número de peças fábricadas deve ser inteiro e maior do que 0.\n");
					TimeUnit.MILLISECONDS.sleep(5);
				}
			} while (true);
	
			if (fabrica.getClasseOperario() == 'A') {
				if (fabrica.getPecasFabricadasMes() <= 30)
					fabrica.setCalculoSalario(500 + 2 * fabrica.getPecasFabricadasMes());
				else if (fabrica.getPecasFabricadasMes() >= 31 && fabrica.getPecasFabricadasMes() <= 40)
					fabrica.setCalculoSalario(500 + 2.3 * fabrica.getPecasFabricadasMes());
				else
					fabrica.setCalculoSalario(500 + 2.8 * fabrica.getPecasFabricadasMes());
			} else if (fabrica.getClasseOperario() == 'B') {
				fabrica.setCalculoSalario(1200);
				pecasFabricadasOperarioB += fabrica.getPecasFabricadasMes();
			} else {
				if (fabrica.getPecasFabricadasMes() <= 50)
					fabrica.setCalculoSalario(40 * fabrica.getPecasFabricadasMes());
				else
					fabrica.setCalculoSalario(45 * fabrica.getPecasFabricadasMes());
			}

			System.out.printf("\n\nO salário do empregado é: R$%.2f", fabrica.getCalculoSalario());

			fabrica.setTotalGastoPagamentoSalarios(fabrica.getTotalGastoPagamentoSalarios() + fabrica.getCalculoSalario());
			fabrica.setNumeroTotalPecasFabricadas(fabrica.getNumeroTotalPecasFabricadas() + fabrica.getPecasFabricadasMes());

			if (fabrica.getPecasFabricadasMes() < menorNumeroPecas) {
				fabrica.setCodigoOperarioMenorNumeroPecas(fabrica.getCodigoOperario());
				menorNumeroPecas = fabrica.getPecasFabricadasMes();
			}

			contadorOperario++;

			System.out.println("\n\nInforme a 0 para sair ou qualquer entrada para inserir um novo operário.\n\n");
			if (validacao.validarNovoOperario(fabrica, scanner.nextLine()) == false)
				break;
		}

		// Continuar
		if (contadorOperario != 0)
			fabrica.setMediaQuantidadeFabricasOperarioB((double) pecasFabricadasOperarioB / contadorOperario);

		System.out.printf("\nTotal gasto com pagamento de salários: R$%.2f", fabrica.getTotalGastoPagamentoSalarios());
		System.out.println("\nNúmero total de peças fábricadas: " + fabrica.getNumeroTotalPecasFabricadas());
		System.out.println("Código do operário com menor número de peças: " + fabrica.getCodigoOperarioMenorNumeroPecas());
		System.out.println("Média da quantidade de peças fábricas pelos operários da classe B: " + fabrica.getMediaQuantidadeFabricasOperarioB());

		System.out.println("\n\nFim do programa.");
		scanner.close();
	}
}