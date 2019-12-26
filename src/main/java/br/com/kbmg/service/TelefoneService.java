package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Telefone;

public interface TelefoneService extends GenericService<Telefone> {

	List<Telefone> findByPessoa(String idPessoa);

}
