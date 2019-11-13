package br.com.kbmg.service.impl;

import java.security.InvalidParameterException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.repository.PessoaJuridicaRepository;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.service.PessoaJuridicaService;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.utils.Validator;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository repository;

	@Autowired
	PessoaFisicaService pessoaFisicaService;

	@Autowired
	PessoaJuridicaService pessoaJuridicaService;

	@Autowired
	MessagesService msg;

	@Override
	public Pessoa create(Pessoa pessoa) {
		validaPessoa(pessoa);
		verificaSeExisteCpfOuCnpj(pessoa);

		return pessoa;
	}

	private void validaPessoa(Pessoa pessoa) {
		if (pessoa.getTipoPessoa() == null)
			throw new InvalidParameterException(msg.get("pessoa.tipo.obrigatorio"));

	}

	private void verificaSeExisteCpfOuCnpj(Pessoa pessoa) {
		
		if (pessoa.getTipoPessoa().equals(TipoPessoa.PF)
				? pessoaFisicaService.verifyIfCpfExists(pessoa.getPessoaFisica().getCpf()) 
				: pessoaJuridicaService.verifyIfCnpjExists(pessoa.getPessoaJuridica().getCnpj()))
			throw new EntityExistsException(msg.get("pessoa.existe"));
	}

	@Override
	public Pessoa findByIdPessoa(String id_pessoa) {
		return repository.findById(Validator.stringParseLong(id_pessoa, "Id da pessoa"))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

}
