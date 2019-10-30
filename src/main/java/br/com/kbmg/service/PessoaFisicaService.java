package br.com.kbmg.service;

import br.com.kbmg.domain.PessoaFisica;

public interface PessoaFisicaService {

	/**
	 * Busca pessoa física por cpf.
	 * 
	 * @param cpf - Cadastro de Pessoa Fisica
	 * 
	 * @return O registro encontrado ou null se não existe
	 */
	PessoaFisica findByCpf(String cpf);

//	/**
//	 * Busca pessoa física por cpf.
//	 * 
//	 * @param cpf - Cadastro de Pessoa Fisica
//	 * 
//	 * @return Optional<PessoaFisica> - se existir
//	 * @return Optional - se existir 
//	 */
//	PessoaFisica findByCpf(String cpf);
}
