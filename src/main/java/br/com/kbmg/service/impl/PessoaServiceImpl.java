package br.com.kbmg.service.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.domain.PessoaJuridica;
import br.com.kbmg.dto.PessoaDTO;
import br.com.kbmg.dto.body.PessoaBodyDto;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.service.PessoaJuridicaService;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.utils.Util;

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
	public PessoaDTO create(PessoaBodyDto body) {
		Pessoa pessoa = this.criaPessoa(body);
		
		verificaSeExisteCpfOuCnpj(pessoa);
		
		pessoa.getEmails().forEach(e -> e.setPessoa(pessoa));
		pessoa.getEnderecos().forEach(e -> e.setPessoa(pessoa));
		pessoa.getTelefones().forEach(t -> t.setPessoa(pessoa));
		
		return (PessoaDTO) Util.convertObject(this.saveEntity(pessoa), PessoaDTO.class);
	}

	private Pessoa criaPessoa(PessoaBodyDto body) {
		Pessoa pessoa = (Pessoa) Util.convertObject(body, Pessoa.class);
		TipoPessoa tipoPessoa = Util.getTipoPessoaByCpfCnpj(body.getCpfCnpj());

		pessoa.setTipoPessoa(tipoPessoa);

		if (tipoPessoa.equals(TipoPessoa.PF))
			pessoa.setPessoaFisica(new PessoaFisica(body.getCpfCnpj(), body.getRg(), pessoa));
		else
			pessoa.setPessoaJuridica(new PessoaJuridica(body.getCpfCnpj(), body.getInscricaoEstadual(), pessoa));

		pessoa.setDataCriacao(LocalDateTime.now());
		return pessoa;
	}

	private void verificaSeExisteCpfOuCnpj(Pessoa pessoa) {

		if (pessoa.getTipoPessoa().equals(TipoPessoa.PF)
				? pessoaFisicaService.verifyIfCpfExists(pessoa.getPessoaFisica().getCpf())
				: pessoaJuridicaService.verifyIfCnpjExists(pessoa.getPessoaJuridica().getCnpj()))
			throw new EntityExistsException(msg.get("pessoa.existe"));
	}

}
