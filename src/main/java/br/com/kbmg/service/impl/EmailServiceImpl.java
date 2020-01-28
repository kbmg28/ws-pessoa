package br.com.kbmg.service.impl;

import static br.com.kbmg.utils.Util.convertList;

import java.security.InvalidParameterException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Email;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.dto.body.EmailBodyDto;
import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.repository.EmailRepository;
import br.com.kbmg.service.EmailService;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.utils.Util;

@Service
public class EmailServiceImpl extends GenericServiceImpl<Email> implements EmailService {

	@Autowired
	EmailRepository repository;

	@Autowired
	PessoaService pessoaService;

	@Override
	public EmailDTO addEmailParaPessoa(String idPessoa, EmailBodyDto body) {

		Pessoa pessoa = pessoaService.findById(idPessoa);
		Email email = (Email) Util.convertObject(body, Email.class);

		email.setStatus(StatusEnum.ATIVO);
		email.setPessoa(new Pessoa(idPessoa));

		if (pessoa.getEmails().stream().filter(e -> e.getEmail().equalsIgnoreCase(email.getEmail())).findFirst()
				.isPresent())
			throw new InvalidParameterException(msg.get("email.cadastrado.para.pessoa"));

		repository.save(email);

		return (EmailDTO) Util.convertObject(email, EmailDTO.class);
	}

	@Override
	public List<?> findByPessoa(String idPessoa) {
		return convertList(repository.findByPessoa(pessoaService.findById(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.emails"))), EmailDTO.class);
	}

}
