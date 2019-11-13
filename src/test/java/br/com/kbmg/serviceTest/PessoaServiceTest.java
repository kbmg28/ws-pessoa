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
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.PessoaFisicaService;
import br.com.kbmg.service.PessoaJuridicaService;
import br.com.kbmg.service.impl.PessoaServiceImpl;

public class PessoaServiceTest {

	@InjectMocks
	PessoaServiceImpl service;

	@Mock
	PessoaRepository repository;

	@Mock
	PessoaFisicaService pessoaFisicaService;

	@Mock
	PessoaJuridicaService pessoaJuridicaService;

	@Mock
	MessagesService msg;

	private static final Long ID_PESSOA_1 = 100L;

	private static final String NOME_PESSOA = "NOME DE TESTE";

	private static final String CPF = "70715922092";
	private static final String CNPJ = "15206418000127";

	private static final String NAO_ENCONTRADO = "Não encontrado";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(msg.get("nao.encontrado")).thenReturn(NAO_ENCONTRADO);
	}

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
	@DisplayName("Não deve adicionar pessoa se CPF existe")
	void deveLancarExceptionPessoaSeCpfExiste() {
		String msgErro = "Pessoa já cadastrada.";
		Pessoa pessoa = PessoaBuilder.umaPessoa(ID_PESSOA_1, NOME_PESSOA).fisica(CPF).agora();

		when(msg.get("pessoa.existe")).thenReturn(msgErro);
		when(pessoaFisicaService.verifyIfCpfExists(CPF)).thenReturn(true);

		EntityExistsException exception = assertThrows(EntityExistsException.class, () -> service.create(pessoa));
		assertEquals(msgErro, exception.getMessage());
	}

	@Test
	@DisplayName("Não deve adicionar pessoa se CNPJ existe")
	void deveLancarExceptionPessoaSeCnpjExiste() {
		String msgErro = "Pessoa já cadastrada.";
		Pessoa pessoa = PessoaBuilder.umaPessoa(ID_PESSOA_1, NOME_PESSOA).juridica(CNPJ).agora();

		when(msg.get("pessoa.existe")).thenReturn(msgErro);
		when(pessoaJuridicaService.verifyIfCnpjExists(CNPJ)).thenReturn(true);

		EntityExistsException exception = assertThrows(EntityExistsException.class, () -> service.create(pessoa));
		assertEquals(msgErro, exception.getMessage());
	}

	@Test
	@DisplayName("Buscar pessoa por id_pessoa")
	void deveBuscarPorPessoaFisicaPorIdPessoa() {
		Pessoa p = PessoaBuilder.umaPessoa(ID_PESSOA_1, NOME_PESSOA).fisica(CPF).agora();

		when(repository.findById(p.getId_pessoa())).thenReturn(Optional.of(p));

		Pessoa retorno = service.findByIdPessoa(ID_PESSOA_1.toString());
		assertAll(() -> assertEquals(p.getNomeCompleto(), retorno.getNomeCompleto(), "NOME COMPLETO"),
				() -> assertEquals(p.getPessoaFisica().getCpf(), retorno.getPessoaFisica().getCpf(), "CPF"));
	}

	@Test
	@DisplayName("Lança exception quando não encontra id_pessoa")
	void deveLancarExceptionSeNaoEncontrarPessoaPorId() {

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByIdPessoa(ID_PESSOA_1.toString()));
		assertEquals(NAO_ENCONTRADO, exception.getMessage());
	}

}