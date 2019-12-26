package br.com.kbmg.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.Telefone;
import br.com.kbmg.repository.TelefoneRepository;
import br.com.kbmg.service.TelefoneService;

@Service
public class TelefoneServiceImpl extends GenericServiceImpl<Telefone> implements TelefoneService {

	@Autowired
	TelefoneRepository repository;

	@Override
	public List<Telefone> findByPessoa(String idPessoa) {
		return repository.findByPessoa(new Pessoa(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.telefones")));
	}

}
