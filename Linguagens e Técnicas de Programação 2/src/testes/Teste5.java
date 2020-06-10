package testes;

import javax.swing.JOptionPane;

public class Teste5 {

	public static void main(String[] args) {
		String entrada = null;

		entrada = "   200    00    ";

		//System.out.println(entrada.trim().replaceAll(" +", "as"));

		entrada = JOptionPane.showInputDialog("Informe a entrada: ");
		Integer.parseInt(JOptionPane.showInputDialog("Informe a entrada: "));

		JOptionPane.showMessageDialog(null, entrada);
	}
}