package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.PessoaFisica;

public class CreatePessoaFisica {

	public static PessoaFisica get(Long id_pf, String cpf) {
		return new PessoaFisica() {
			private static final long serialVersionUID = 1L;
			{
				setId_pf(id_pf);
				setCpf(cpf);
			}
		};
	}
}
