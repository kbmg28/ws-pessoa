package br.com.kbmg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.domain.PessoaJuridica;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.repository.PessoaRepository;

@Component
@Profile(value="test")
public class RunFake implements CommandLineRunner{

	@Autowired
	PessoaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		this.criaPessoaFisica("TESTE DO NOME", "71941528007", "242492757");
		this.criaPessoaJuridica("NOME DA PESSOA JURIDICA", "19056334000105", "995253323");
		this.criaPessoaFisica("DEV4DEV GERADOR DE CPF", "47268406061", "363537454");
	}

	private void criaPessoaFisica(String nomeCompleto, String cpf, String rg) {
		PessoaFisica pf = new PessoaFisica();
		Pessoa p = new Pessoa();
		
		p.setNomeCompleto(nomeCompleto);
		p.setTipo(TipoPessoa.PF);
		p.setPessoaFisica(pf);
		
		pf.setCpf(cpf);
		pf.setRg(rg);
		pf.setPessoa(p);
		
		repository.save(p);
	}

	private void criaPessoaJuridica(String nomeCompleto, String cnpj, String inscricaoEstadual) {
		PessoaJuridica pj = new PessoaJuridica();
		Pessoa p = new Pessoa();
		
		p.setNomeCompleto(nomeCompleto);
		p.setTipo(TipoPessoa.PJ);
		p.setPessoaJuridica(pj);
		
		pj.setCnpj(cnpj);
		pj.setInscricaoEstadual(inscricaoEstadual);
		pj.setPessoa(p);
		
		repository.save(p);
	}
	
	
}
