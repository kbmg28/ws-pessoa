package br.com.kbmg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Endereco;
import br.com.kbmg.repository.EnderecoRepository;
import br.com.kbmg.service.EnderecoService;

@Service
public class EnderecoServiceImpl extends GenericServiceImpl<Endereco> implements EnderecoService {

	@Autowired
	EnderecoRepository repository;

	@Autowired
	MessagesService msg;

}
