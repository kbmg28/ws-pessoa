package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Pessoa;

public class CreatePessoa {

	public static Pessoa get(Long idPessoa, String nomeCompleto) {
		return new Pessoa() {
			private static final long serialVersionUID = 1L;
			{
				setIdPessoa(idPessoa);
				setNomeCompleto(nomeCompleto);
			}
		};
	}
}
