package br.com.kbmg.service;

import br.com.kbmg.domain.Pessoa;


public interface PessoaService {
	
	Pessoa create(Pessoa pessoa);

	Pessoa findByIdPessoa(String id_pessoa);

}
