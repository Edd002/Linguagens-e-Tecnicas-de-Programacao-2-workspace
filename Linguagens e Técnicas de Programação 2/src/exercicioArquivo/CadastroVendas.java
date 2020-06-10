package exercicioArquivo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Scanner;

import exercicioArquivo.CadastroFrutas.Fruta;

public class CadastroVendas {
	private static Scanner leia = new Scanner(System.in);

	public static final String NOMEARQUIVO = "VENDAS.SACOLAO";
	
	public static class Venda {
		int codVenda;
		String nomeFruta;
		String cpf;
		int quantidade;
		float totalPago;
		String dataVenda;

	}
	
	public static void main(String[] args) {
				
		int opcao;
		Venda v=null;

		do {
			opcao = menu();
			switch (opcao) {
			case 0:
				System.out.println("Fechando...");
				break;
			case 1:
				v = realizarVenda();
				salvarArquivo(v, -1);
				break;
			case 2:
				
				break;
			case 3:
				lerTodoArquivo();
				break;
			case 4:
				CadastroFrutas.main(args);
				break;
			case 5:
				CadastroClientes.main(args);
				break;
			case 6:
				GestaoSacolao.gerarRelatorio();
				break;

			default:
				
				break;
			}
		} while (opcao != 0);
		
		
		

	}
	
	private static Venda realizarVenda(){
		Venda v= new Venda();			
		Fruta f=null;
		
		v.codVenda = Utils.recebeInt("Digite o CÓDIGO: ");

		do {
			CadastroFrutas.lerTodoArquivo();
			v.nomeFruta = Utils.recebeString("Informe o nome da FRUTA: ");
			f = CadastroFrutas.consultarFruta(v.nomeFruta);
			if(f==null){
				System.out.println("Fruta não encontrada! ");
			}
		} while (f==null);
		
		
		do {
			CadastroClientes.lerTodoArquivo();
			v.cpf = Utils.recebeString("Informe a CPF do cliente: ");
		} while (CadastroClientes.consultarCliente(v.cpf)==null);
		

		v.quantidade = Utils.recebeInt("Informe o QUANTIDADE de frutas: ");
		
		
		v.totalPago	 = v.quantidade*f.preco;
				
		v.dataVenda = Utils.recebeString("Informe a DATA da venda: ");
		
		return v;
	}
	
	private static void salvarArquivo(Venda v, long pointer) {

		try {

			RandomAccessFile arquivo = new RandomAccessFile(NOMEARQUIVO, "rw");

			if (pointer < 0) {
				arquivo.seek(arquivo.length());
			} else {
				arquivo.seek(pointer);
			}

			arquivo.writeInt(v.codVenda);
			arquivo.writeUTF(v.nomeFruta);
			arquivo.writeUTF(v.cpf);
			arquivo.writeInt(v.quantidade);
			arquivo.writeFloat(v.totalPago);
			arquivo.writeUTF(v.dataVenda);

			arquivo.close();

			System.out.println("Registro Salvo com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}
	
	private static int menu() {
		int opcao;

		System.out.println("======== MENU VENDAS =========");
		System.out.println("1 - Incluir");
		System.out.println("2 - Consultar");
		System.out.println("3 - Listar todos");
		System.out.println("4 - Cadastrar FRUTAS");
		System.out.println("5 - Cadastrar CLIENTES");
		System.out.println("6 - Gerar Relatório");
		System.out.println("0 - SAIR");
		System.out.print("Opção: ");

		opcao = leia.nextInt();

		return opcao;
	}

	public static void lerTodoArquivo() {
		System.out.println("\n================= Lista VENDAS ===================");
		try {
			RandomAccessFile arquivo = new RandomAccessFile(NOMEARQUIVO, "rw");
			Venda v = new Venda();

			while (arquivo.getFilePointer() != arquivo.length()) {
				v.codVenda = arquivo.readInt();
				v.nomeFruta = arquivo.readUTF();
				v.cpf = arquivo.readUTF();
				v.quantidade = arquivo.readInt();
				v.totalPago = arquivo.readFloat();	
				v.dataVenda = arquivo.readUTF();
				
				System.out.println("=========================================================");
				imprimirVenda(v);	
			}

			arquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na gravação do registro!");
		}

	}
	
	private static void imprimirVenda(Venda v) {

		DecimalFormat df = new DecimalFormat("R$ #,##0.00");

		System.out.println("Cod Venda  :" + v.codVenda);
		System.out.println("Nome Fruta :" + v.nomeFruta);
		System.out.println("CPF        :" + v.cpf);
		System.out.println("Quantidade :" + v.quantidade);
		System.out.println("Total Pago :" + df.format(v.totalPago));
		System.out.println("Data Venda :" + v.dataVenda + "\n");
	}
}
