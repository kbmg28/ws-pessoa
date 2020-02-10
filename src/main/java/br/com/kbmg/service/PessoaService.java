package br.com.kbmg.service;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.dto.PessoaDTO;
import br.com.kbmg.dto.body.PessoaBodyDto;

public interface PessoaService extends GenericService<Pessoa> {

	PessoaDTO create(PessoaBodyDto body);

}
