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
import br.com.kbmg.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository repository;

	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	MessagesService msg;

	@Override
	public Pessoa create(Pessoa pessoa) {
		validaPessoa(pessoa);
		verificaSeExisteCpfOuCnpj(pessoa);

		return null;
	}

	private void validaPessoa(Pessoa pessoa) {
		if (pessoa.getTipo() == null)
			throw new InvalidParameterException(msg.get("pessoa.tipo.obrigatorio"));

	}

	private void verificaSeExisteCpfOuCnpj(Pessoa pessoa) {
		if (pessoa.getTipo().equals(TipoPessoa.PF)
				? pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf()).isPresent()
				: pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj()).isPresent())
			throw new EntityExistsException(msg.get("pessoa.existe"));
	}

	@Override
	public Pessoa findByCodPessoa(Long codPessoa) {
		return repository.findById(codPessoa).orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

}
