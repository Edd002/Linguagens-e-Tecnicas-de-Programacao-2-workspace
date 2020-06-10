package exercicioArquivo;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Scanner;


public class CadastroClientes {

	private static Scanner leia = new Scanner(System.in);

	public static class Cliente {
		char ativo;
		String cpf;
		String nome;
		float totalCompras;

	}

	public static void main(String[] args) {

		// Criar o objeto f do tipo Cliente
		Cliente f = null;
		int opcao;
		String cpf;

		do {
			opcao = menu();
			switch (opcao) {
			case 0:
				System.out.println("Fechando...");
				break;
			case 1:
				f = inserirCliente();
				salvarArquivo(f, -1);
				break;
			case 2:
				System.out.println("Qual cliente deseja alterar (cpf)? ");
				leia.nextLine();
				cpf = leia.nextLine();
				f = consultarCliente(cpf);
				if (f != null) {
					f = editarCliente(f, 'S');
					salvarArquivo(f, consultarClientePointer(cpf));
				} else {
					System.out.println("Cliente não encontrada");
				}
				break;
			case 3:
				System.out.println("Qual cliente deseja excluir(cpf)? ");
				leia.nextLine();
				cpf = leia.nextLine();
				f = consultarCliente(cpf);
				if (f != null) {
					f = editarCliente(f, 'N');
					salvarArquivo(f, consultarClientePointer(cpf));
				} else {
					System.out.println("Cliente não encontrada");
				}
				break;
			case 4:
				System.out.println("Qual cliente deseja consultar(cpf)? ");
				leia.nextLine();
				cpf = leia.nextLine();
				f = consultarCliente(cpf);
				if (f != null) {
					imprimirCliente(f);
				} else {
					System.out.println("Cliente não encontrada!");
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

	private static Cliente inserirCliente() {
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);

		Cliente c = new Cliente();

		c.ativo = 'S';

		System.out.println("Informe o nome do CPF: ");
		c.cpf = leia.nextLine();
		
		System.out.println("Digite o NOME: ");
		c.nome = leia.nextLine();
		
		c.totalCompras = 0;

		return c;

	}

	private static void imprimirCliente(Cliente c) {

		DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		
		System.out.println("CPF          :" + c.cpf);
		System.out.println("Nome         :" + c.nome);
		System.out.println("Total Compras:" + df.format(c.totalCompras) + "\n");
	}

	public static void salvarArquivo(Cliente c, long pointer) {

		try {

			RandomAccessFile arquivo = new RandomAccessFile("CLIENTE.SACOLAO", "rw");

			if (pointer < 0) {
				arquivo.seek(arquivo.length());
			} else {
				arquivo.seek(pointer);
			}

			arquivo.writeChar(c.ativo);
			arquivo.writeUTF(c.cpf);
			arquivo.writeUTF(c.nome);
			arquivo.writeFloat(c.totalCompras);

			arquivo.close();

			System.out.println("Registro Salvo com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}

	public static void lerTodoArquivo() {
		System.out.println("\n================= Clientes ===================");
		try {
			RandomAccessFile arquivo = new RandomAccessFile("CLIENTE.SACOLAO", "rw");
			Cliente c = new Cliente();

			while (arquivo.getFilePointer() != arquivo.length()) {
				c.ativo = arquivo.readChar();
				c.cpf = arquivo.readUTF();
				c.nome = arquivo.readUTF();
				c.totalCompras = arquivo.readFloat();				
				if(c.ativo=='S'){
					System.out.println("=========================================================");
					imprimirCliente(c);	
				}
				

			}

			arquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}

	public static Cliente consultarCliente(String nome) {

		Cliente c = new Cliente();

		try {
			RandomAccessFile arquivo = new RandomAccessFile("CLIENTE.SACOLAO", "rw");

			while (true) {
				c.ativo = arquivo.readChar();
				c.cpf = arquivo.readUTF();
				c.nome = arquivo.readUTF();				
				c.totalCompras = arquivo.readFloat();

				if (c.cpf.equalsIgnoreCase(nome) && c.ativo == 'S') {
					arquivo.close();
					return c;
				}

			}
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

		return null;
	}

	public static long consultarClientePointer(String nome) {

		System.out.println("================= Consulta Cliente por CPF ===================");
		Cliente c = new Cliente();
		long pointer;

		try {
			RandomAccessFile arquivo = new RandomAccessFile("CLIENTE.SACOLAO", "rw");

			while (true) {
				pointer = arquivo.getFilePointer();

				c.ativo = arquivo.readChar();				
				c.cpf = arquivo.readUTF();
				c.nome = arquivo.readUTF();				
				c.totalCompras = arquivo.readFloat();

				if (c.cpf.equalsIgnoreCase(nome) && c.ativo == 'S') {
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

	private static Cliente editarCliente(Cliente c, char ativo) {
		@SuppressWarnings("resource")
		Scanner leia = new Scanner(System.in);
		Cliente nova = new Cliente();

		c.ativo = ativo;
		nova.ativo = 'S';

		if (ativo == 'S' || ativo == 's') {
			
			if (desejaAlterar("CPF: " + c.cpf)) {
				System.out.println("Informe o CPF: ");
				nova.cpf = leia.nextLine();
			}else{
				nova.cpf = c.cpf;
			}
			
			if (desejaAlterar("NOME: " + c.nome)) {
				System.out.println("Informe o nome: ");
				nova.nome = leia.nextLine();
			}else{
				nova.nome = c.nome;
			}

			
			nova.totalCompras = c.totalCompras;
			
			//excluir o atual para incluir o novo alterado
			salvarArquivo(c, consultarClientePointer(c.nome));
			return nova;
		}

		
		
		return c;
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

		System.out.println("======== MENU CLIENTES =========");
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
