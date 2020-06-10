package exercicioArquivo;

import java.util.Scanner;

public class Utils {

	static Scanner leia = new Scanner(System.in);

	public static int recebeInt(String texto){
		int numero;
		System.out.println(texto);
		numero = leia.nextInt();
		
		return numero;
	}
	
	public static float recebeFloat(String texto){
		float numero;
		System.out.println(texto);
		numero = leia.nextFloat();
		
		return numero;
	}
	
	public static String recebeString(String texto){
		String tx;
		leia.nextLine();
		System.out.println(texto);
		tx = leia.nextLine();
		
		return tx;
	}

}
