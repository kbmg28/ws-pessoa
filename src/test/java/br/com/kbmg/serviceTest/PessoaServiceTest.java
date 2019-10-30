package br.com.kbmg.serviceTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.security.InvalidParameterException;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kbmg.builder.PessoaBuilder;
import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.factoryTest.CreatePessoa;
import br.com.kbmg.repository.PessoaFisicaRepository;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.impl.PessoaServiceImpl;

public class PessoaServiceTest {

	@InjectMocks
	PessoaServiceImpl service;

	@Mock
	PessoaRepository repository;

	@Mock
	PessoaFisicaRepository pessoaFisicaRepository;

	@Mock
	MessagesService msg;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private static final Long ID_PESSOA_1 = 100L;

	private static final String NOME_PESSOA = "NOME DE TESTE";

	private static final String CPF_1 = "70715922092";
	private static final String CPF_2 = "89941099006";

	@Test
	@DisplayName("Não deve adicionar pessoa se tipo não informado")
	void deveLancarExceptionSeTipoPessoaVazio() {
		String msgErro = "Tipo da pessoa obrigatório";

		when(msg.get("pessoa.tipo.obrigatorio")).thenReturn(msgErro);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.create(PessoaBuilder.umaPessoa(ID_PESSOA_1, NOME_PESSOA).agora()));
		assertEquals(msgErro, exception.getMessage());
	}

	@Test
	@DisplayName("Não deve adicionar pessoa física se cpf existe")
	void deveLancarExceptionSeCpfExiste() {
		String msgErro = "Cpf já cadastrado";
		Pessoa pessoa = PessoaBuilder.umaPessoa(1L, NOME_PESSOA).fisica(CPF_1).agora();

		when(msg.get("pessoa.fisica.cpf.existe")).thenReturn(msgErro);
		when(pessoaFisicaRepository.findByCpf(CPF_1)).thenReturn(Optional.of(pessoa.getPessoaFisica()));

		EntityExistsException exception = assertThrows(EntityExistsException.class, () -> service.create(pessoa));
		assertEquals(msgErro, exception.getMessage());
	}

	@Test
	@DisplayName("Buscar pessoa por id_pessoa")
	void deveBuscarPorPessoaFisicaPorIdPessoa() {
		Pessoa p = CreatePessoa.getFisica(1L, "TEST JUNIT5", CPF_1);

		when(repository.findById(p.getId_pessoa())).thenReturn(Optional.of(p));

		Pessoa retorno = service.findByCodPessoa(p.getId_pessoa());
		assertAll(() -> assertEquals(p.getNomeCompleto(), retorno.getNomeCompleto(), "NOME COMPLETO"),
				() -> assertEquals(p.getPessoaFisica().getCpf(), retorno.getPessoaFisica().getCpf(), "CPF"));
	}

	@Test
	@DisplayName("Lança exception quando não encontra id_pessoa")
	void deveLancarExceptionSeNaoEncontrarPessoaPorId() {
		String msgErro = "Não encontrado";

		when(msg.get("nao.encontrado")).thenReturn(msgErro);

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByCodPessoa(1L));
		assertEquals(msgErro, exception.getMessage());
	}

}
