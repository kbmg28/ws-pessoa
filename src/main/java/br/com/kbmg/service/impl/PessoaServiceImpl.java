package br.com.kbmg.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository repository;
	
	@Autowired
	MessagesService msg;
	
	@Override
	public Pessoa findByCodPessoa(Long codPessoa) {
		
		return repository.findById(codPessoa).orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

}
