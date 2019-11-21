package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.PessoaJuridica;

public class CreatePessoaJuridica {

	public static PessoaJuridica get(Long idPj, String cnpj, String inscricaoEstadual) {
		return new PessoaJuridica() {
			private static final long serialVersionUID = 1L;
			{
				setIdPj(idPj);
				setCnpj(cnpj);
				setInscricaoEstadual(inscricaoEstadual);
			}
		};
	}
}
