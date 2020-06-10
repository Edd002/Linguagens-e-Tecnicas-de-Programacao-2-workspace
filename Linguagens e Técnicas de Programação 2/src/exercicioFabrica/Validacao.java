package exercicioFabrica;

public class Validacao {
	public boolean validarOperarioInteiro(Fabrica fabrica, String codigoOperario) {
		try {
			fabrica.setCodigoOperario(Integer.parseInt(codigoOperario));
			return true;
		} catch (Exception exception) {
			System.err.println("Exceção: " + exception);
		}
		return false;
	}

	public boolean validarPecasInteiro(Fabrica fabrica, String codigoOperario) {
		try {
			fabrica.setPecasFabricadasMes(Integer.parseInt(codigoOperario));
			if (fabrica.getPecasFabricadasMes() <= 0)
				return false;
			return true;
		} catch (Exception exception) {
			System.err.println("Exceção: " + exception);
		}
		return false;
	}

	public boolean validarClasseUmCaracterLetraABC(Fabrica fabrica, String classeOperario) {
		try {
			if (classeOperario.length() == 1) {
				for (int i = 65; i < 68; i++) {
					if (Character.toUpperCase(classeOperario.charAt(0)) == (char) i) {
						fabrica.setClasseOperario(Character.toUpperCase(classeOperario.charAt(0)));
						return true;
					}
				}
			}
		} catch (Exception exception) {
			System.err.println("Exceção: " + exception);
		}
		return false;
	}

	public boolean validarNovoOperario(Fabrica fabrica, String novoOperario) {
		try {
			if (novoOperario.length() == 1) {
				if (novoOperario.charAt(0) == '0')
					return false;
				else
					return true;
			}
		} catch (Exception exception) {
			System.err.println("Exceção: " + exception);
		}
		return true;
	}
}