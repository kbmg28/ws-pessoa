package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.dto.body.EnderecoBodyDto;

public interface EnderecoService extends GenericService<Endereco> {

	List<?> findByPessoa(String idPessoa);

	EnderecoDto addEnderecoParaPessoa(String idPessoa, EnderecoBodyDto body);

}
