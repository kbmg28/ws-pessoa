package br.com.kbmg.service;

import javax.persistence.EntityNotFoundException;

import br.com.kbmg.domain.PessoaFisica;

public interface PessoaFisicaService extends GenericService<PessoaFisica> {

	/**
	 * Busca pessoa física por cpf.
	 * 
	 * @param cpf - Cadastro de Pessoa Fisica
	 * 
	 * @return O registro encontrado.
	 * @throws EntityNotFoundException- Se não existir.
	 */
	PessoaFisica findByCpf(String cpf);

	/**
	 * Verifica se o cpf existe.
	 * 
	 * @param cpf - Cadastro de Pessoa Fisica
	 * 
	 * @return a representação booleana da verificação.
	 */
	Boolean verifyIfCpfExists(String cpf);
}
