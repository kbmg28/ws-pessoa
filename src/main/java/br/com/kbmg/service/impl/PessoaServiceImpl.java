package br.com.kbmg.service.impl;

import java.security.InvalidParameterException;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.service.PessoaJuridicaService;
import br.com.kbmg.service.PessoaService;

@Service
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

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

		pessoa.getEmails().forEach(e -> e.setPessoa(pessoa));
		pessoa.getEnderecos().forEach(e -> e.setPessoa(pessoa));
		pessoa.getTelefones().forEach(t -> t.setPessoa(pessoa));

		return repository.save(pessoa);
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

}
