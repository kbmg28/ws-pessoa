package br.com.kbmg.factoryTest.dto;

import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.enums.TipoDeUso;

public class CreateEmailDto {

	public static EmailDTO get(String idEmail, String email, TipoDeUso tipoDeUso, String pessoaId) {
		return new EmailDTO() {
			{
				setIdEmail(idEmail);
				setEmail(email);
				setTipoDeUso(tipoDeUso);
				setPessoaId(pessoaId);
			}
		};
	}
}
