package br.com.kbmg.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.service.PessoaFisicaService;

@Service
public class PessoaFisicaServiceImpl extends GenericServiceImpl<PessoaFisica> implements PessoaFisicaService {

	@Autowired
	PessoaFisicaRepository repository;

	@Autowired
	MessagesService msg;

	@Override
	public PessoaFisica findByCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}
	
	@Override
	public Boolean verifyIfCpfExists(String cpf) {
		return repository.findByCpf(cpf).isPresent();
	}
}
