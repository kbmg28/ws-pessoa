package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Pessoa;

public class CreatePessoa {

	public static Pessoa get(Long id_pessoa, String nomeCompleto) {
		return new Pessoa() {
			private static final long serialVersionUID = 1L;
			{
				setIdPessoa(id_pessoa);
				setNomeCompleto(nomeCompleto);
			}
		};
	}
}
