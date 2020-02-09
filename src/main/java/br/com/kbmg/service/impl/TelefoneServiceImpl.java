package br.com.kbmg.service.impl;

import static br.com.kbmg.utils.Util.convertList;

import java.security.InvalidParameterException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.Telefone;
import br.com.kbmg.dto.TelefoneDto;
import br.com.kbmg.dto.body.TelefoneBodyDto;
import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.repository.TelefoneRepository;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.service.TelefoneService;
import br.com.kbmg.utils.Util;

@Service
public class TelefoneServiceImpl extends GenericServiceImpl<Telefone> implements TelefoneService {

	@Autowired
	TelefoneRepository repository;

	@Autowired
	PessoaService pessoaService;

	@Override
	public TelefoneDto addTelefoneParaPessoa(String idPessoa, TelefoneBodyDto telefoneBody) {

		Pessoa pessoa = pessoaService.findById(idPessoa);
		Telefone telefone = (Telefone) Util.convertObject(telefoneBody, Telefone.class);

		telefone.setStatus(StatusEnum.ATIVO);
		telefone.setPessoa(pessoa);

		if (pessoa.getTelefones().stream().filter(t -> compareTelefone(telefone, t)).findFirst().isPresent())
			throw new InvalidParameterException(msg.get("telefone.cadastrado.para.pessoa"));

		repository.save(telefone);

		return (TelefoneDto) Util.convertObject(telefone, TelefoneDto.class);
	}

	private Boolean compareTelefone(Telefone novo, Telefone param) {
		return novo.getDdd().equals(param.getDdd()) && novo.getNumero().equals(param.getNumero());
	}

	@Override
	public List<?> findByPessoa(String idPessoa) {
		return convertList(repository.findByPessoa(pessoaService.findById(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.telefones"))), TelefoneDto.class);
	}

}
