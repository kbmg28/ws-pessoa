package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Email;

public class CreateEmail {

	public static Email get(Long idEmail, String email) {
		return new Email() {
			private static final long serialVersionUID = 1L;
			{
				setIdEmail(idEmail);
				setEmail(email);
			}
		};
	}
}
