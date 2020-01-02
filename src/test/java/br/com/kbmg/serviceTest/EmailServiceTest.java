package br.com.kbmg.serviceTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kbmg.builder.PessoaBuilder;
import br.com.kbmg.config.MessagesService;
import br.com.kbmg.domain.Email;
import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.enums.TipoDeUso;
import br.com.kbmg.repository.EmailRepository;
import br.com.kbmg.service.impl.EmailServiceImpl;

public class EmailServiceTest {

	@InjectMocks
	EmailServiceImpl service;

	@Mock
	EmailRepository repository;

	@Mock
	MessagesService msg;

	private static final String ID_PESSOA = "1";
	int i;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Busca todos os emails da pessoa por ID_PESSOA")
	void deveBuscarEnderecosComIdDaPessoa() {

		Pessoa pessoa = new Pessoa(ID_PESSOA);
		List<Email> list = new ArrayList<>(
				PessoaBuilder.umaPessoa(pessoa.getIdPessoa(), null).comEmail("teste1@teste.com", TipoDeUso.PARTICULAR)
						.comEmail("teste2@teste.com", TipoDeUso.OUTROS).agora().getEmails());

		when(repository.findByPessoa(pessoa)).thenReturn(Optional.of(list));

		List<Email> retorno = service.findByPessoa(ID_PESSOA);

		assertEquals(list.size(), 3);

		for (i = 0; i < retorno.size(); i++) {
			assertAll(() -> assertEquals(list.get(i).getIdEmail(), retorno.get(i).getIdEmail()),
					() -> assertEquals(list.get(i).getEmail(), retorno.get(i).getEmail()),
					() -> assertEquals(list.get(i).getTipoDeUso(), retorno.get(i).getTipoDeUso()));
		}
	}

	@Test
	@DisplayName("Lança exception quando não encontrar endereços de uma Pessoa por ID_PESSOA")
	void deveLancarExceptionSeNaoEncontrarEnderecosPorIdPessoa() {
		String msgException = "Pessoa sem endereços cadastrados.";
		when(msg.get("pessoa.sem.enderecos")).thenReturn(msgException);

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByPessoa(ID_PESSOA));
		assertEquals(msgException, exception.getMessage());
	}

}
