package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.PessoaJuridica;

public class CreatePessoaJuridica {

	public static PessoaJuridica get(Long id_pj, String cnpj, String inscricaoEstadual) {
		return new PessoaJuridica() {
			private static final long serialVersionUID = 1L;
			{
				setId_pj(id_pj);
				setCnpj(cnpj);
				setInscricaoEstadual(inscricaoEstadual);
			}
		};
	}
}
