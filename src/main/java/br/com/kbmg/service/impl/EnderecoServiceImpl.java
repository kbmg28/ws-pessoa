package br.com.kbmg.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.repository.EnderecoRepository;
import br.com.kbmg.service.EnderecoService;

@Service
public class EnderecoServiceImpl extends GenericServiceImpl<Endereco> implements EnderecoService {

	@Autowired
	EnderecoRepository repository;

	@Override
	public List<Endereco> findByPessoa(String idPessoa) {
		return repository.findByPessoa(new Pessoa(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.enderecos")));
	}

}
