package exercicioFabrica;

public class Fabrica {
	private char classeOperario;
	private int codigoOperario;
	private int pecasFabricadasMes;
	private int numeroTotalPecasFabricadas;
	private int codigoOperarioMenorNumeroPecas;
	private double mediaQuantidadeFabricasOperarioB;
	private double calculoSalario;
	private double totalGastoPagamentoSalarios;

	public char getClasseOperario() {
		return classeOperario;
	}
	public void setClasseOperario(char classeOperario) {
		this.classeOperario = classeOperario;
	}
	public int getCodigoOperario() {
		return codigoOperario;
	}
	public void setCodigoOperario(int codigoOperario) {
		this.codigoOperario = codigoOperario;
	}
	public int getPecasFabricadasMes() {
		return pecasFabricadasMes;
	}
	public void setPecasFabricadasMes(int pecasFabricadasMes) {
		this.pecasFabricadasMes = pecasFabricadasMes;
	}
	public int getNumeroTotalPecasFabricadas() {
		return numeroTotalPecasFabricadas;
	}
	public void setNumeroTotalPecasFabricadas(int numeroTotalPecasFabricadas) {
		this.numeroTotalPecasFabricadas = numeroTotalPecasFabricadas;
	}
	public int getCodigoOperarioMenorNumeroPecas() {
		return codigoOperarioMenorNumeroPecas;
	}
	public void setCodigoOperarioMenorNumeroPecas(int codigoOperarioMenorNumeroPecas) {
		this.codigoOperarioMenorNumeroPecas = codigoOperarioMenorNumeroPecas;
	}
	public double getMediaQuantidadeFabricasOperarioB() {
		return mediaQuantidadeFabricasOperarioB;
	}
	public void setMediaQuantidadeFabricasOperarioB(double mediaQuantidadeFabricasOperarioB) {
		this.mediaQuantidadeFabricasOperarioB = mediaQuantidadeFabricasOperarioB;
	}
	public double getCalculoSalario() {
		return calculoSalario;
	}
	public void setCalculoSalario(double calculoSalario) {
		this.calculoSalario = calculoSalario;
	}
	public double getTotalGastoPagamentoSalarios() {
		return totalGastoPagamentoSalarios;
	}
	public void setTotalGastoPagamentoSalarios(double totalGastoPagamentoSalarios) {
		this.totalGastoPagamentoSalarios = totalGastoPagamentoSalarios;
	}
}