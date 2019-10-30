package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoPessoa;

public class CreatePessoa {

	public static Pessoa getFisica(Long id_pessoa, String nomeCompleto, String cpf) {
		return new Pessoa() {
			private static final long serialVersionUID = 1L;
			{
				setId_pessoa(id_pessoa);
				setNomeCompleto(nomeCompleto);
				setTipo(TipoPessoa.PF);
				setPessoaFisica(CreatePessoaFisica.get(id_pessoa, cpf));
			}
		};
	}

	public static Pessoa getJuridica(Long id_pessoa, String nomeCompleto, String cnpj, String inscricaoEstadual) {
		return new Pessoa() {
			private static final long serialVersionUID = 1L;
			{
				setId_pessoa(id_pessoa);
				setNomeCompleto(nomeCompleto);
				setTipo(TipoPessoa.PJ);
				setPessoaJuridica(CreatePessoaJuridica.get(id_pessoa, cnpj, inscricaoEstadual));
			}
		};
	}
}
