package testes;

import java.util.concurrent.TimeUnit;

public class Teste2 {
	public static void main(String[] args) throws InterruptedException {
		System.err.println("Error");
		TimeUnit.MILLISECONDS.sleep(5);

		System.out.println((char) 65);

		System.out.println(Math.round(1.5));

		System.out.println("Vin�cius Tolentino".substring(1, 4) + "Vin�cius Tolentino".substring(8, 14));

		String nome = "Vin�cius Camara da Cunha Tolentino";
		System.out.println(nome.substring(nome.lastIndexOf(" ") + 1));
	}
}