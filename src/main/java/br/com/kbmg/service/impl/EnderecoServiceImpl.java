package br.com.kbmg.service.impl;

import java.security.InvalidParameterException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.dto.body.EnderecoBodyDto;
import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.repository.EnderecoRepository;
import br.com.kbmg.service.EnderecoService;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.utils.Util;

@Service
public class EnderecoServiceImpl extends GenericServiceImpl<Endereco> implements EnderecoService {

	@Autowired
	EnderecoRepository repository;

	@Autowired
	PessoaService pessoaService;

	@Override
	public EnderecoDto addEnderecoParaPessoa(String idPessoa, EnderecoBodyDto body) {
		
		Pessoa pessoa = pessoaService.findById(idPessoa);
		Endereco endereco = (Endereco) Util.convertObject(body, Endereco.class);

		endereco.setStatus(StatusEnum.ATIVO);
		endereco.setPessoa(new Pessoa(idPessoa));
		
		if (pessoa.getEnderecos().stream().filter(e -> this.compareEndereco(e, endereco)).findFirst()
				.isPresent())
			throw new InvalidParameterException(msg.get("endereco.cadastrado.para.pessoa"));

		repository.save(endereco);

		return (EnderecoDto) Util.convertObject(endereco, EnderecoDto.class);
	}
	
	private Boolean compareEndereco(Endereco atual, Endereco novo) {
		boolean logradouroIgual = novo.getLogradouro().equalsIgnoreCase(atual.getLogradouro());
		boolean numeroIgual = novo.getNumero().equalsIgnoreCase(atual.getNumero());
		boolean bairroIgual = novo.getBairro().equalsIgnoreCase(atual.getBairro());
		
		return (logradouroIgual && numeroIgual && bairroIgual);
		
	}

	@Override
	public List<?> findByPessoa(String idPessoa) {
		return Util.convertList(repository.findByPessoa(new Pessoa(idPessoa))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("pessoa.sem.enderecos"))), EnderecoDto.class);
	}


}
