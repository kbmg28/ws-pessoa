package br.com.kbmg.builder;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.factoryTest.CreatePessoa;
import br.com.kbmg.factoryTest.CreatePessoaFisica;
import br.com.kbmg.factoryTest.CreatePessoaJuridica;

public class PessoaBuilder {

	private Pessoa pessoa;

	public static PessoaBuilder umaPessoa(Long id, String nomeCompleto) {
		PessoaBuilder builder = new PessoaBuilder();
		builder.pessoa = CreatePessoa.get(id, nomeCompleto);
//		builder.pessoa.setEmails(new LinkedHashSet<>());
//		builder.pessoa.setEnderecos(new LinkedHashSet<>());
//		builder.pessoa.setTelefones(new LinkedHashSet<>());
		return builder;
	}

	public PessoaBuilder fisica(String cpf) {
		this.pessoa.setPessoaFisica(CreatePessoaFisica.get(this.pessoa.getId_pessoa(), cpf));
		this.pessoa.setTipoPessoa(TipoPessoa.PF);
		return this;
	}

	public PessoaBuilder juridica(String cnpj) {
		this.pessoa.setPessoaJuridica(CreatePessoaJuridica.get(this.pessoa.getId_pessoa(), cnpj, null));
		this.pessoa.setTipoPessoa(TipoPessoa.PJ);
		return this;
	}

//	public PessoaBuilder comEnderecoFiscal() {
//		this.pessoa.getEnderecos()
//				.add(CreateEnderecoTest.get(this.pessoa.getId(),
//						CreateInscricaoEstadualTest.get(this.pessoa.getId(), "ISENTO", "N"), 1058, null,
//						"RUA QUEMM ODYSSEY", 13, 1302603, "69097110", "CIDADE NOVA", "001", "TESTE", null, 1));
//		return this;
//	}
//	public PessoaBuilder comEnderecoEntrega() {
//		this.pessoa.getEnderecos().add(CreateEnderecoTest.get(this.pessoa.getId() + 1L, "69048130", "RUA PEDREIRA",
//				"PAZ", "002", 1058, 13, 1302603, "TESTE2", 2));
//		return this;
//	}
//	
//	public PessoaBuilder comTelefone(Integer numero, Integer tipo) {
//		this.pessoa.getTelefones().add(CreateTelefonePessoaTest.get(this.pessoa.getId(), null, 92, numero, null, tipo));
//		return this;
//	}
//	
//	public PessoaBuilder comEmail(String email, Integer finalidade) {
//		this.pessoa.getEmails().add(
//				CreateEmailPessoaTest.get(this.pessoa.getId(), this.pessoa.getNomeCompleto(), finalidade, email, null));
//		return this;
//	}

	public Pessoa agora() {
		return pessoa;
	}

}
