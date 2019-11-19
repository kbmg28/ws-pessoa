package br.com.kbmg.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.utils.Util;

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

//	@Override
//	public PessoaFisica findById(String id_pf) {
//		return repository.findById(Validator.stringParseLong(id_pf, "Id da pessoa física"))
//				.orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
//	}
	
	@Override
	public void deleteById(Long id) {
		PessoaFisica fisica = this.findById(id.toString(), "ID pessoa física");
		fisica.setPessoa(null);
		Util.printObjectConsole(repository.save(fisica));
		repository.deleteById(id);
//		repository.
//		super.deleteById(id);
	}

	@Override
	public Boolean verifyIfCpfExists(String cpf) {
		return repository.findByCpf(cpf).isPresent();
	}
}
