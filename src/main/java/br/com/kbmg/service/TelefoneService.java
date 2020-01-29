package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Telefone;
import br.com.kbmg.dto.TelefoneDto;
import br.com.kbmg.dto.body.TelefoneBodyDto;

public interface TelefoneService extends GenericService<Telefone> {

	List<Telefone> findByPessoa(String idPessoa);

	TelefoneDto addTelefoneParaPessoa(String idPessoa, TelefoneBodyDto telefoneBody);

}
