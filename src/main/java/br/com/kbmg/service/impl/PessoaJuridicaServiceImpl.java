package br.com.kbmg.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.PessoaJuridica;
import br.com.kbmg.repository.PessoaJuridicaRepository;
import br.com.kbmg.service.PessoaJuridicaService;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

	@Autowired
	PessoaJuridicaRepository repository;

	@Autowired
	MessagesService msg;

	@Override
	public PessoaJuridica findByCnpj(String cnpj) {
		return repository.findByCnpj(cnpj).orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

	@Override
	public Boolean verifyIfCnpjExists(String cnpj) {
		return repository.findByCnpj(cnpj).isPresent();
	}
}
