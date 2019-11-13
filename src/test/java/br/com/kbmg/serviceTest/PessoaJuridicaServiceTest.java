package br.com.kbmg.serviceTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.PessoaJuridica;
import br.com.kbmg.factoryTest.CreatePessoaJuridica;
import br.com.kbmg.repository.PessoaJuridicaRepository;
import br.com.kbmg.service.impl.PessoaJuridicaServiceImpl;

public class PessoaJuridicaServiceTest {

	@InjectMocks
	PessoaJuridicaServiceImpl service;

	@Mock
	PessoaJuridicaRepository repository;

	@Mock
	MessagesService msg;

	private static final Long ID_PESSOA_JURIDICA = 10L;
	private static final String CNPJ = "89900617000177";
	private static final String INSCRICAO_ESTADUAL = "487867469182";

	private static final String NAO_ENCONTRADO = "Não encontrado";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(msg.get("nao.encontrado")).thenReturn(NAO_ENCONTRADO);
	}

	@Test
	@DisplayName("Busca por CNPJ")
	void deveBuscarPorCNPJ() {
		PessoaJuridica pessoaJuridica = CreatePessoaJuridica.get(ID_PESSOA_JURIDICA, CNPJ, INSCRICAO_ESTADUAL);

		when(repository.findByCnpj(CNPJ)).thenReturn(Optional.of(pessoaJuridica));

		assertEquals(pessoaJuridica.getCnpj(), service.findByCnpj(CNPJ).getCnpj());
	}

	@Test
	@DisplayName("Lança exception quando não encontrar CNPJ")
	void deveLancarExceptionSeNaoEncontrarCNPJ() {

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.findByCnpj(CNPJ));
		assertEquals(NAO_ENCONTRADO, exception.getMessage());
	}

	@Test
	@DisplayName("Busca por ID_PJ")
	void deveBuscarPorIdPj() {
		PessoaJuridica pessoaJuridica = CreatePessoaJuridica.get(ID_PESSOA_JURIDICA, CNPJ, INSCRICAO_ESTADUAL);

		when(repository.findById(ID_PESSOA_JURIDICA)).thenReturn(Optional.of(pessoaJuridica));
		PessoaJuridica retorno = service.findById(ID_PESSOA_JURIDICA.toString());

		assertAll(() -> assertEquals(pessoaJuridica.getCnpj(), retorno.getCnpj()),
				() -> assertEquals(pessoaJuridica.getId_pj(), retorno.getId_pj()));
	}

	@Test
	@DisplayName("Lança exception quando não encontrar ID_PJ")
	void deveLancarExceptionSeNaoEncontrarIdPj() {

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findById(ID_PESSOA_JURIDICA.toString()));
		assertEquals(NAO_ENCONTRADO, exception.getMessage());
	}

	@Test
	@DisplayName("Deve retornar \'true\' se CNPJ já está cadastrado")
	void deveRetornarTrueSeCNPJExiste() {
		PessoaJuridica pessoaJuridica = CreatePessoaJuridica.get(ID_PESSOA_JURIDICA, CNPJ, INSCRICAO_ESTADUAL);

		when(repository.findByCnpj(CNPJ)).thenReturn(Optional.of(pessoaJuridica));

		assertEquals(true, service.verifyIfCnpjExists(CNPJ));
	}

	@Test
	@DisplayName("Deve retornar \'false\' se CNPJ não está cadastrado")
	void deveRetornarFalseSeCNPJNaoExiste() {
		
		when(repository.findByCnpj(CNPJ)).thenReturn(Optional.empty());
		
		assertEquals(false, service.verifyIfCnpjExists(CNPJ));
	}

	@Test
	@DisplayName("Deve retornar \'false\' se na verificacao de CNPJ existente, valor estiver null")
	void deveRetornarFalseSeVerificaCNPJNull() {
		
		assertEquals(false, service.verifyIfCnpjExists(null));
	}

}
