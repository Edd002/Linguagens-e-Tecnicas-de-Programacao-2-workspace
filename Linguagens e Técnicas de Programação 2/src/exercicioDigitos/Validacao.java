package exercicioDigitos;

public class Validacao {

	public boolean validarDigitos(Digitos digitos, String codigoCaracter) {
		try {
			if (codigoCaracter.length() != 11)
				return false;

			Long.parseLong(codigoCaracter);

			digitos.setCodigoCaracter(codigoCaracter);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
}