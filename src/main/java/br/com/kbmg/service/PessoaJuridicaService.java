package br.com.kbmg.service;

import br.com.kbmg.domain.PessoaJuridica;

public interface PessoaJuridicaService extends GenericService<PessoaJuridica>{

	/**
	 * Busca pessoa jurídica por CNPJ.
	 * 
	 * @param cnpj - Cadastro Nacional de Pessoa Jurídica
	 * 
	 * @return O registro encontrado.
	 */
	PessoaJuridica findByCnpj(String cnpj);

	/**
	 * Verifica se o CNPJ existe.
	 * 
	 * @param cnpj - Cadastro Nacional de Pessoa Jurídica
	 * 
	 * @return a representação booleana da verificação.
	 */
	Boolean verifyIfCnpjExists(String cnpj);
}
