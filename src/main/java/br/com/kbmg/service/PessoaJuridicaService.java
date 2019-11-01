package br.com.kbmg.service;

import javax.persistence.EntityNotFoundException;

import br.com.kbmg.domain.PessoaJuridica;

public interface PessoaJuridicaService {

	/**
	 * Busca pessoa jurídica por ID.
	 * 
	 * @param id_pj - identificador único.
	 * 
	 * @return O registro encontrado.
	 * @throws EntityNotFoundException- Se não existir.
	 */
	PessoaJuridica findById(String id_pj);

	/**
	 * Busca pessoa jurídica por CNPJ.
	 * 
	 * @param cnpj - Cadastro Nacional de Pessoa Jurídica
	 * 
	 * @return O registro encontrado.
	 */
	PessoaJuridica findByCnpj(String cnpj);
}
