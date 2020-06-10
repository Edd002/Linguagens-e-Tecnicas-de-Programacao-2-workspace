package exercicioArquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import exercicioArquivo.CadastroClientes.Cliente;
import exercicioArquivo.CadastroVendas.Venda;

public class GestaoSacolao {

	public static final String NOMEARQUIVO = "RELATORIOSACOLAO.DAT";

	public static class RelatorioSacolao {
		private static String data;
		private static float totalVendas;
		private static String nomeClienteMaisComprou;
		private static float valorTotalFrutaMaisVendida;
	}

	public static void main(String[] args) {
		gerarRelatorio();

		System.out.println();

		System.out.println(RelatorioSacolao.data);
		System.out.println(RelatorioSacolao.totalVendas);
		System.out.println(RelatorioSacolao.nomeClienteMaisComprou);
		System.out.println(RelatorioSacolao.valorTotalFrutaMaisVendida);
	}

	public static void gerarRelatorio() {
		System.out.println("\n================= RELATÓRIO - SACOLÃO ===================");

		gerarDataAtual();
		totalVendasRealizadas();
		nomeClienteMaisComprou();
		valorTotalFrutaMaisVendeu();

		salvarRelatorio();

		System.out.println();
	}

	private static void totalVendasRealizadas() {
		RelatorioSacolao.totalVendas = 0;

		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(CadastroVendas.NOMEARQUIVO, "rw");
			Venda venda = new Venda();

			while (randomAccessFile.getFilePointer() != randomAccessFile.length()) {
				venda.codVenda = randomAccessFile.readInt();
				venda.nomeFruta = randomAccessFile.readUTF();
				venda.cpf = randomAccessFile.readUTF();
				venda.quantidade = randomAccessFile.readInt();
				venda.totalPago = randomAccessFile.readFloat();	
				venda.dataVenda = randomAccessFile.readUTF();

				RelatorioSacolao.totalVendas += venda.totalPago;
			}

			randomAccessFile.close();

			System.out.println(String.format("Total de Vendas Realizas: R$%.2f", RelatorioSacolao.totalVendas));
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Erro ao gerar relatório do total de vendas.");
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("Erro ao gerar relatório do total de vendas.");
			ioException.printStackTrace();
		}
	}

	private static void nomeClienteMaisComprou() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(CadastroVendas.NOMEARQUIVO, "rw");
			Venda venda = new Venda();
			HashMap <String, Float> mapCPFTotalPago = new HashMap<>();
			String CPFClienteMaisVendas = null;
			float valorVendaMaisAlta = 0;

			while (randomAccessFile.getFilePointer() != randomAccessFile.length()) {
				venda.codVenda = randomAccessFile.readInt();
				venda.nomeFruta = randomAccessFile.readUTF();
				venda.cpf = randomAccessFile.readUTF();
				venda.quantidade = randomAccessFile.readInt();
				venda.totalPago = randomAccessFile.readFloat();	
				venda.dataVenda = randomAccessFile.readUTF();

				if (mapCPFTotalPago.get(venda.cpf) == null)
					mapCPFTotalPago.put(venda.cpf, venda.totalPago);
				else
					mapCPFTotalPago.put(venda.cpf, venda.totalPago += mapCPFTotalPago.get(venda.cpf));
			}

			Set<String> chaves = mapCPFTotalPago.keySet();
			for (String chave : chaves) {
				if (mapCPFTotalPago.get(chave) > valorVendaMaisAlta) {
					CPFClienteMaisVendas = chave;
					valorVendaMaisAlta = mapCPFTotalPago.get(chave);
				}
			}

			RelatorioSacolao.nomeClienteMaisComprou = buscarNomeCliente(CPFClienteMaisVendas);

			randomAccessFile.close();

			System.out.println("Nome do cliente que mais comprou: " + RelatorioSacolao.nomeClienteMaisComprou + " com R$" + String.format("%.2f", valorVendaMaisAlta));
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Erro ao gerar relatório do cliente que mais comprou.");
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("Erro ao gerar relatório do cliente que mais comprou.");
			ioException.printStackTrace();
		}

	}

	private static void valorTotalFrutaMaisVendeu() {
		RelatorioSacolao.valorTotalFrutaMaisVendida = 0;

		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(CadastroVendas.NOMEARQUIVO, "rw");
			Venda venda = new Venda();
			HashMap <String, Float> mapNomeFrutaTotalPago = new HashMap<>();
			String nomeFrutaMaisVendeu = null;
			float valorTotalFrutaMaisVedeu = 0;

			while (randomAccessFile.getFilePointer() != randomAccessFile.length()) {
				venda.codVenda = randomAccessFile.readInt();
				venda.nomeFruta = randomAccessFile.readUTF();
				venda.cpf = randomAccessFile.readUTF();
				venda.quantidade = randomAccessFile.readInt();
				venda.totalPago = randomAccessFile.readFloat();	
				venda.dataVenda = randomAccessFile.readUTF();

				if (mapNomeFrutaTotalPago.get(venda.nomeFruta) == null)
					mapNomeFrutaTotalPago.put(venda.nomeFruta, venda.totalPago);
				else
					mapNomeFrutaTotalPago.put(venda.nomeFruta, venda.totalPago += mapNomeFrutaTotalPago.get(venda.nomeFruta));

				RelatorioSacolao.valorTotalFrutaMaisVendida += venda.totalPago;
			}

			Set<String> chaves = mapNomeFrutaTotalPago.keySet();
			for (String chave : chaves) {
				if (mapNomeFrutaTotalPago.get(chave) > valorTotalFrutaMaisVedeu) {
					nomeFrutaMaisVendeu = chave;
					valorTotalFrutaMaisVedeu = mapNomeFrutaTotalPago.get(chave);
				}
			}

			RelatorioSacolao.valorTotalFrutaMaisVendida = valorTotalFrutaMaisVedeu;

			randomAccessFile.close();

			System.out.println(String.format("Fruta que mais vendeu com seu valor total arrecadado: " + nomeFrutaMaisVendeu + " com R$%.2f", RelatorioSacolao.valorTotalFrutaMaisVendida));
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Erro ao gerar relatório da fruta mais vendida.");
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("Erro ao gerar relatório da fruta mais vendida.");
			ioException.printStackTrace();
		}
	}

	private static void salvarRelatorio() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(NOMEARQUIVO, "rw");
			randomAccessFile.seek(randomAccessFile.length());

			randomAccessFile.writeUTF(RelatorioSacolao.data);
			randomAccessFile.writeFloat(RelatorioSacolao.totalVendas);
			randomAccessFile.writeUTF(RelatorioSacolao.nomeClienteMaisComprou);
			randomAccessFile.writeFloat(RelatorioSacolao.valorTotalFrutaMaisVendida);

			randomAccessFile.close();

			System.out.println("\nRelatório gerado.\n");
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Erro ao salvar relatório.");
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("Erro ao salvar relatório.");
			ioException.printStackTrace();
		}
	}

	/* =============================== MÉTODOS AUXILIARES ====================================== */
	private static String buscarNomeCliente(String CPF) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile("CLIENTE.SACOLAO", "rw");
			Cliente cliente = new Cliente();

			while (true) {
				cliente.ativo = randomAccessFile.readChar();
				cliente.cpf = randomAccessFile.readUTF();
				cliente.nome = randomAccessFile.readUTF();				
				cliente.totalCompras = randomAccessFile.readFloat();

				if (cliente.cpf.equalsIgnoreCase(CPF) && cliente.ativo == 'S') {
					randomAccessFile.close();
					return cliente.nome;
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Erro ao buscar o nome do cliente pelo CPF.");
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("Erro ao buscar o nome do cliente pelo CPF.");
			ioException.printStackTrace();
		}

		return null;
	}

	private static void gerarDataAtual() {
		Locale localeBR = new Locale("pt", "BR");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", localeBR);
		RelatorioSacolao.data = simpleDateFormat.format(new Date());

		System.out.println("Data do relatório: " + RelatorioSacolao.data);
	}
	

	/* =============================== MÉTODOS AUXILIARES ====================================== */
}