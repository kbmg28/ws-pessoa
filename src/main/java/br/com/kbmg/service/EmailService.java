package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Email;
import br.com.kbmg.dto.EmailDTO;

public interface EmailService extends GenericService<Email> {

	List<Email> findByPessoa(String idPessoa);

	EmailDTO addEmailParaPessoa(EmailDTO emailDto);

}
