package br.com.kbmg.service;

import java.util.List;

import br.com.kbmg.domain.Email;
import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.dto.body.EmailBodyDto;

public interface EmailService extends GenericService<Email> {

	List<?> findByPessoa(String idPessoa);

	EmailDTO addEmailParaPessoa(String idPessoa, EmailBodyDto emailDto);

}
