package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Email;

public interface EmailService extends GenericService<Email> {

	List<Email> findByPessoa(String idPessoa);

}
