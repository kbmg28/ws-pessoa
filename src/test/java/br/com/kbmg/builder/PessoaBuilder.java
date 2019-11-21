package br.com.kbmg.builder;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoEndereco;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.factoryTest.CreateEmail;
import br.com.kbmg.factoryTest.CreateEndereco;
import br.com.kbmg.factoryTest.CreatePessoa;
import br.com.kbmg.factoryTest.CreatePessoaFisica;
import br.com.kbmg.factoryTest.CreatePessoaJuridica;
import br.com.kbmg.factoryTest.CreateTelefone;

public class PessoaBuilder {

	private Pessoa pessoa;

	public static PessoaBuilder umaPessoa(Long id, String nomeCompleto) {
		PessoaBuilder builder = new PessoaBuilder();
		builder.pessoa = CreatePessoa.get(id, nomeCompleto);
		return builder;
	}

	public PessoaBuilder fisica(String cpf) {
		this.pessoa.setPessoaFisica(CreatePessoaFisica.get(this.pessoa.getIdPessoa(), cpf));
		this.pessoa.setTipoPessoa(TipoPessoa.PF);
		return this;
	}

	public PessoaBuilder juridica(String cnpj) {
		this.pessoa.setPessoaJuridica(CreatePessoaJuridica.get(this.pessoa.getIdPessoa(), cnpj, null));
		this.pessoa.setTipoPessoa(TipoPessoa.PJ);
		return this;
	}

	public PessoaBuilder comEnderecoFiscal() {
		this.pessoa.getEnderecos().add(CreateEndereco.get(this.pessoa.getIdPessoa(), "RUA QUEMM ODYSSEY", null, null,
				"69097110", "CIDADE NOVA", "001", "TESTE", TipoEndereco.FISCAL));
		return this;
	}

	public PessoaBuilder comTelefone(Integer numero) {
		this.pessoa.getTelefones().add(CreateTelefone.get(this.pessoa.getIdPessoa(), 92, numero, null));
		return this;
	}

	public PessoaBuilder comEmail(String email) {
		this.pessoa.getEmails().add(CreateEmail.get(this.pessoa.getIdPessoa(), email));
		return this;
	}

	public Pessoa agora() {
		return pessoa;
	}

}
