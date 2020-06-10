package testes;

import java.util.concurrent.TimeUnit;

public class Teste2 {
	public static void main(String[] args) throws InterruptedException {
		System.err.println("Error");
		TimeUnit.MILLISECONDS.sleep(5);

		System.out.println((char) 65);

		System.out.println(Math.round(1.5));

		System.out.println("Vinícius Tolentino".substring(1, 4) + "Vinícius Tolentino".substring(8, 14));

		String nome = "Vinícius Camara da Cunha Tolentino";
		System.out.println(nome.substring(nome.lastIndexOf(" ") + 1));
	}
}