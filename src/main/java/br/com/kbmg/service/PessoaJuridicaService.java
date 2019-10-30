package br.com.kbmg.service;

import br.com.kbmg.domain.PessoaJuridica;

public interface PessoaJuridicaService {

	/**
	 * Busca pessoa jurídica por cnpj.
	 * 
	 * @param cnpj - Cadastro Nacional de Pessoa Jurídica
	 * 
	 * @return O registro encontrado ou null se não existe
	 */
	PessoaJuridica findByCnpj(String cnpj);
}
