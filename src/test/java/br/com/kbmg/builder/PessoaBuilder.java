package br.com.kbmg.builder;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoDeUso;
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
		this.pessoa.getEnderecos().add(CreateEndereco.get(this.pessoa.getIdPessoa(), "69097150", "Rua Laranjal",
				"TESTE COMPLEMENTO", "CIDADE NOVA", "001", "MANAUS", "AM", 1302603, TipoEndereco.FISCAL));
		return this;
	}

	public PessoaBuilder comEnderecoOutros() {
		this.pessoa.getEnderecos().add(CreateEndereco.get(this.pessoa.getIdPessoa() + 1, "69067516",
				"Rua Governador Henoch Reis", null, "Petrópolis", "12A", "MANAUS", "AM", 1302603, TipoEndereco.OUTROS));
		return this;
	}

	public PessoaBuilder comTelefone(Integer numero) {
		this.pessoa.getTelefones().add(CreateTelefone.get(this.pessoa.getIdPessoa(), 92, numero, "ALGUÉM"));
		return this;
	}

	public PessoaBuilder comEmail(String email, TipoDeUso tipoDeUso) {
		this.pessoa.getEmails().add(CreateEmail.get(this.pessoa.getIdPessoa(), email, tipoDeUso));
		return this;
	}

	public Pessoa agora() {
		return pessoa;
	}

}
