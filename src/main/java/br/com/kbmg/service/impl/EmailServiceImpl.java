package br.com.kbmg.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Email;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.repository.EmailRepository;
import br.com.kbmg.service.EmailService;

@Service
public class EmailServiceImpl extends GenericServiceImpl<Email> implements EmailService {

	@Autowired
	EmailRepository repository;

	@Override
	public List<Email> findByPessoa(String idPessoa) {
		return repository.findByPessoa(new Pessoa(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.emails")));
	}

}
