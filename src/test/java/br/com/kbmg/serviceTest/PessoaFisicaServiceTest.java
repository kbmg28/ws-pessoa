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
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.factoryTest.CreatePessoaFisica;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.service.impl.PessoaFisicaServiceImpl;

public class PessoaFisicaServiceTest {

	@InjectMocks
	PessoaFisicaServiceImpl service;

	@Mock
	PessoaFisicaRepository repository;

	@Mock
	MessagesService msg;

	private static final Long ID_PESSOA_FISICA = 20L;
	private static final String CPF = "70715922092";

	private static final String NAO_ENCONTRADO = "Não encontrado";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(msg.get("nao.encontrado")).thenReturn(NAO_ENCONTRADO);
	}

	@Test
	@DisplayName("Buscar por CPF")
	void deveBuscarCPF() {
		PessoaFisica pessoaFisica = CreatePessoaFisica.get(ID_PESSOA_FISICA, CPF);

		when(repository.findByCpf(CPF)).thenReturn(Optional.of(pessoaFisica));

		assertEquals(pessoaFisica.getCpf(), service.findByCpf(CPF).getCpf());
	}

	@Test
	@DisplayName("Lança exception quando não encontrar CPF")
	void deveLancarExceptionSeNaoEncontrarCPF() {

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.findByCpf(CPF));
		assertEquals(NAO_ENCONTRADO, exception.getMessage());
	}

//	@Test
//	@DisplayName("Busca por ID_PF")
//	void deveBuscarPorIdPf() {
//		PessoaFisica pessoaFisica = CreatePessoaFisica.get(ID_PESSOA_FISICA, CPF);
//
//		when(repository.findById(ID_PESSOA_FISICA)).thenReturn(Optional.of(pessoaFisica));
//		PessoaFisica retorno = service.findById(ID_PESSOA_FISICA.toString());
//		
//		assertAll(() -> assertEquals(pessoaFisica.getCpf(), retorno.getCpf()),
//				() -> assertEquals(pessoaFisica.getId_pf(), retorno.getId_pf()));
//	}
//
//	@Test
//	@DisplayName("Lança exception quando não encontrar ID_PF")
//	void deveLancarExceptionSeNaoEncontrarIdPf() {
//
//		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
//				() -> service.findById(ID_PESSOA_FISICA.toString()));
//		assertEquals(NAO_ENCONTRADO, exception.getMessage());
//	}

	@Test
	@DisplayName("Deve retornar \'true\' se CPF já está cadastrado")
	void deveRetornarTrueSeCPFExiste() {
		PessoaFisica pessoaFisica = CreatePessoaFisica.get(ID_PESSOA_FISICA, CPF);

		when(repository.findByCpf(CPF)).thenReturn(Optional.of(pessoaFisica));

		assertEquals(true, service.verifyIfCpfExists(CPF));
	}

	@Test
	@DisplayName("Deve retornar \'false\' se CPF não está cadastrado")
	void deveRetornarFalseSeCPFNaoExiste() {
		
		when(repository.findByCpf(CPF)).thenReturn(Optional.empty());
		
		assertEquals(false, service.verifyIfCpfExists(CPF));
	}

	@Test
	@DisplayName("Deve retornar \'false\' se na verificacao de CPF existente, valor estiver null")
	void deveRetornarFalseSeVerificaCPFNull() {
		
		assertEquals(false, service.verifyIfCpfExists(null));
	}

}
