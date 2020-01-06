package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.dto.EnderecoDto;

public interface EnderecoService extends GenericService<Endereco> {

	List<Endereco> findByPessoa(String idPessoa);
	EnderecoDto addEnderecoParaPessoa(EnderecoDto enderecoDto);

}
