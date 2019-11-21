package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.PessoaFisica;

public class CreatePessoaFisica {

	public static PessoaFisica get(Long idPf, String cpf) {
		return new PessoaFisica() {
			private static final long serialVersionUID = 1L;
			{
				setIdPf(idPf);
				setCpf(cpf);
			}
		};
	}
}
