package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Endereco;

public interface EnderecoService extends GenericService<Endereco> {

	List<Endereco> findByPessoa(String idPessoa);

}
