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
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.factoryTest.CreatePessoa;
import br.com.kbmg.repository.PessoaRepository;
import br.com.kbmg.service.impl.PessoaServiceImpl;

public class PessoaServiceTest {

	@InjectMocks
	PessoaServiceImpl service;

	@Mock
	PessoaRepository repository;

	@Mock
	MessagesService msg;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Buscar pessoa por id_pessoa")
	void deveBuscarPorPessoaFisicaPorIdPessoa() {
		Pessoa p = CreatePessoa.getFisica(1L, "TEST JUNIT5", "70715922092");

		when(repository.findById(p.getId_pessoa())).thenReturn(Optional.of(p));
		
		Pessoa retorno = service.findByCodPessoa(p.getId_pessoa());
		assertAll(() -> assertEquals(p.getNomeCompleto(), retorno.getNomeCompleto(), "NOME COMPLETO"),
				() -> assertEquals(p.getPessoaFisica().getCpf(), retorno.getPessoaFisica().getCpf(), "CPF"));
	}

	@Test
	void deveLancarExceptionSeNaoEncontrarPessoaPorId() {
		String msgErro = "Não encontrado";
		
		when(msg.get("nao.encontrado")).thenReturn(msgErro);

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByCodPessoa(1L));
		assertEquals(msgErro, exception.getMessage());
	}

}
