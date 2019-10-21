package br.com.kbmg.service;

import br.com.kbmg.domain.Pessoa;


public interface PessoaService {

	Pessoa findByCodPessoa(Long codPessoa);

}
