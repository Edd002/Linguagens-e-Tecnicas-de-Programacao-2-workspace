package exercicioArquivo;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CadastroFrutas {

	private static Scanner leia = new Scanner(System.in);

	public static class Fruta {
		char ativo;
		String nome;
		String produtor;
		String cor;
		float preco;

	}

	public static void main(String[] args) {

		// Criar o objeto f do tipo Fruta
		Fruta f = null;
		int opcao;
		String nomeFruta;

		do {
			opcao = menu();
			switch (opcao) {
			case 0:
				System.out.println("Fechando...");
				break;
			case 1:
				f = inserirFruta();
				salvarArquivo(f, -1);
				break;
			case 2:
				System.out.println("Qual fruta deseja alterar? ");
				leia.nextLine();
				nomeFruta = leia.nextLine();
				f = consultarFruta(nomeFruta);
				if (f != null) {
					f = editarFruta(f, 'S');
					salvarArquivo(f, consultarFrutaPointer(nomeFruta));
				} else {
					System.out.println("Fruta não encontrada");
				}
				break;
			case 3:
				System.out.println("Qual fruta deseja excluir? ");
				leia.nextLine();
				nomeFruta = leia.nextLine();
				f = consultarFruta(nomeFruta);
				if (f != null) {
					f = editarFruta(f, 'N');
					salvarArquivo(f, consultarFrutaPointer(nomeFruta));
				} else {
					System.out.println("Fruta não encontrada");
				}
				break;
			case 4:
				System.out.println("Qual fruta deseja consultar? ");
				leia.nextLine();
				nomeFruta = leia.nextLine();
				f = consultarFruta(nomeFruta);
				if (f != null) {
					imprimirFruta(f);
				} else {
					System.out.println("Fruta não encontrada!");
				}
				break;
			case 5:
				lerTodoArquivo();
				break;

			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);

	}

	private static Fruta inserirFruta() {
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);

		Fruta f = new Fruta();

		f.ativo = 'S';

		System.out.println("Digite o NOME da fruta: ");
		f.nome = leia.nextLine();

		System.out.println("Informe o nome do PRODUTOR da fruta: ");
		f.produtor = leia.nextLine();

		System.out.println("Informe a COR da fruta: ");
		f.cor = leia.nextLine();

		System.out.println("Informe o PREÇO da fruta: ");
		f.preco = leia.nextFloat();

		return f;

	}

	private static void imprimirFruta(Fruta f) {

		DecimalFormat df = new DecimalFormat("R$ #,##0.00");

		System.out.println("Nome     :" + f.nome);
		System.out.println("Produtor :" + f.produtor);
		System.out.println("Cor      :" + f.cor);
		System.out.println("Preço    :" + df.format(f.preco) + "\n");
	}

	private static void salvarArquivo(Fruta f, long pointer) {

		try {

			RandomAccessFile arquivo = new RandomAccessFile("ABC.SACOLAO", "rw");

			if (pointer < 0) {
				arquivo.seek(arquivo.length());
			} else {
				arquivo.seek(pointer);
			}

			arquivo.writeChar(f.ativo);
			arquivo.writeUTF(f.nome);
			arquivo.writeUTF(f.produtor);
			arquivo.writeUTF(f.cor);
			arquivo.writeFloat(f.preco);

			arquivo.close();

			System.out.println("Registro Salvo com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}

	public static void lerTodoArquivo() {
		System.out.println("\n================= Produtos do Sacolão ===================");
		try {
			RandomAccessFile arquivo = new RandomAccessFile("ABC.SACOLAO", "rw");
			Fruta f = new Fruta();

			while (arquivo.getFilePointer() != arquivo.length()) {
				f.ativo = arquivo.readChar();
				f.nome = arquivo.readUTF();
				f.produtor = arquivo.readUTF();
				f.cor = arquivo.readUTF();
				f.preco = arquivo.readFloat();				
				if(f.ativo=='S'){
					System.out.println("=========================================================");
					imprimirFruta(f);	
				}
				

			}

			arquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}

	public static Fruta consultarFruta(String nome) {
		
		Fruta f = new Fruta();

		try {
			RandomAccessFile arquivo = new RandomAccessFile("ABC.SACOLAO", "rw");

			while (true) {
				f.ativo = arquivo.readChar();
				f.nome = arquivo.readUTF();
				f.produtor = arquivo.readUTF();
				f.cor = arquivo.readUTF();
				f.preco = arquivo.readFloat();

				if (f.nome.equalsIgnoreCase(nome) && f.ativo == 'S') {
					arquivo.close();
					return f;
				}

			}
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

		return null;
	}

	private static long consultarFrutaPointer(String nome) {

		System.out.println("================= Consulta FRUTA por NOME ===================");
		Fruta f = new Fruta();
		long pointer;

		try {
			RandomAccessFile arquivo = new RandomAccessFile("ABC.SACOLAO", "rw");

			while (true) {
				pointer = arquivo.getFilePointer();

				f.ativo = arquivo.readChar();
				f.nome = arquivo.readUTF();
				f.produtor = arquivo.readUTF();
				f.cor = arquivo.readUTF();
				f.preco = arquivo.readFloat();

				if (f.nome.equalsIgnoreCase(nome) && f.ativo == 'S') {
					arquivo.close();
					return pointer;
				}

			}
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

		return -1;
	}

	private static Fruta editarFruta(Fruta f, char ativo) {
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		Fruta nova = new Fruta();

		f.ativo = ativo;
		nova.ativo = 'S';

		if (ativo == 'S' || ativo == 's') {
			if (desejaAlterar("NOME: " + f.nome)) {
				System.out.println("Informe o nome da fruta: ");
				nova.nome = leia.nextLine();
			}else{
				nova.nome = f.nome;
			}

			if (desejaAlterar("PRODUTOR: " + f.produtor)) {
				System.out.println("Informe o nome do PRODUTOR da fruta: ");
				nova.produtor = leia.nextLine();
			}else{
				nova.produtor = f.produtor;
			}

			if (desejaAlterar("COR: " + f.cor)) {
				System.out.println("Informe a COR da fruta: ");
				nova.cor = leia.nextLine();
			}else{
				nova.cor = f.cor;
			}

			if (desejaAlterar("PREÇO: " + f.preco)) {
				System.out.println("Informe o PREÇO da fruta: ");
				f.preco = leia.nextFloat();
			}else{
				nova.preco = f.preco;
			}
			//excluir o atual para incluir o novo alterado
			salvarArquivo(f, consultarFrutaPointer(f.nome));
			return nova;
		}

		
		
		return f;
	}

	private static boolean desejaAlterar(String texto) {
		char opcao;

		System.out.println("Atual: "+texto + " (Alterar S/N)? ");
		opcao = leia.next().toUpperCase().charAt(0);

		if (opcao == 'S') {
			return true;
		} else {
			return false;
		}

	}

	private static int menu() {
		int opcao;

		System.out.println("======== MENU FRUTAS =========");
		System.out.println("1 - Incluir");
		System.out.println("2 - Alterar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Consultar");
		System.out.println("5 - Listar todos");
		System.out.println("0 - SAIR");
		System.out.print("Opção: ");

		opcao = leia.nextInt();

		return opcao;
	}
}
