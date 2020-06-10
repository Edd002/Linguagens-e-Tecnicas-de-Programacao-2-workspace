package testes;
public class Teste1 {
	public static void main(String[] args) {
		int a;
		int b;

		a = 3;
		b = 4;

		System.out.println("Valor inicial de a: " + a);
		System.out.println("Valor inicial de b: " + b);

		b = ++a;
		System.out.print("b = ++a -> ");
		System.out.print("a:" + a + " _ ");
		System.out.print("b:" + b);

		System.out.println();

		a = 3;
		b = 4;
		b = a++;
		System.out.print("b = a++ -> ");
		System.out.print("a:" + a + " _ ");
		System.out.print("b:" + b);

		System.out.println();

		a = 3;
		b = 4;
		a = b++;
		System.out.print("a = b++ -> ");
		System.out.print("a:" + a + " _ ");
		System.out.print("b:" + b);

		System.out.println();

		a = 3;
		b = 4;
		a = ++b;
		System.out.print("a = ++b -> ");
		System.out.print("a:" + a + " _ ");
		System.out.print("b:" + b);
	}
}