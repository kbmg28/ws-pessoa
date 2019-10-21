package br.com.kbmg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.repository.PessoaRepository;

@Component
@Profile(value="test")
public class RunFake implements CommandLineRunner{

	@Autowired
	PessoaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		Pessoa p = new Pessoa();
		p.setNomeCompleto("Teste do nome");
		repository.save(p);
	}
}
