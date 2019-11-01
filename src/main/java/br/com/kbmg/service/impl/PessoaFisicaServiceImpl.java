package br.com.kbmg.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.utils.Validator;

@Service
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

	@Autowired
	PessoaFisicaRepository repository;

	@Autowired
	MessagesService msg;

	@Override
	public PessoaFisica findByCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

	@Override
	public PessoaFisica findById(String id_pf) {
		return repository.findById(Validator.stringParseLong(id_pf, "Id da pessoa física"))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}
}
