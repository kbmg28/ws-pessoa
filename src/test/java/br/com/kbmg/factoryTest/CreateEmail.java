package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Email;
import br.com.kbmg.enums.TipoDeUso;

public class CreateEmail {

	public static Email get(Long idEmail, String email, TipoDeUso tipoDeUso) {
		return new Email() {
			private static final long serialVersionUID = 1L;
			{
				setIdEmail(idEmail);
				setEmail(email);
				setTipoDeUso(tipoDeUso);
			}
		};
	}
}
