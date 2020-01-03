package br.com.kbmg.serviceTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.security.InvalidParameterException;
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
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.enums.TipoDeUso;
import br.com.kbmg.factoryTest.dto.CreateEmailDto;
import br.com.kbmg.repository.EmailRepository;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.service.impl.EmailServiceImpl;

public class EmailServiceTest {

	@InjectMocks
	EmailServiceImpl service;

	@Mock
	PessoaService pessoaService;

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
	void deveBuscarEmailsComIdDaPessoa() {

		Pessoa pessoa = new Pessoa(ID_PESSOA);
		List<Email> list = new ArrayList<>(
				PessoaBuilder.umaPessoa(pessoa.getIdPessoa(), null).comEmail("teste1@teste.com", TipoDeUso.PARTICULAR)
						.comEmail("teste2@teste.com", TipoDeUso.OUTROS).agora().getEmails());

		when(repository.findByPessoa(pessoa)).thenReturn(Optional.of(list));

		List<?> retorno = service.findByPessoa(ID_PESSOA);

		assertEquals(list.size(), retorno.size());

		for (i = 0; i < retorno.size(); i++) {
			EmailDTO emailDto = (EmailDTO) retorno.get(i);

			assertAll(() -> assertEquals(list.get(i).getEmail(), emailDto.getEmail()),
					() -> assertEquals(list.get(i).getTipoDeUso(), emailDto.getTipoDeUso()));
		}
	}

	@Test
	@DisplayName("Lança exception quando não encontrar emails de uma Pessoa por ID_PESSOA")
	void deveLancarExceptionSeNaoEncontrarEmailsPorIdPessoa() {
		String msgException = "Pessoa sem emails cadastrados.";
		when(msg.get("pessoa.sem.emails")).thenReturn(msgException);

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByPessoa(ID_PESSOA));
		assertEquals(msgException, exception.getMessage());
	}

	@Test
	@DisplayName("Deve adicionar Email para a pessoa")
	void deveAdicionarEmailParaPessoa() {
		EmailDTO dto = CreateEmailDto.get("21", "add@email.com", TipoDeUso.PARTICULAR, ID_PESSOA);
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA")
				.comEmail("email@email.com", TipoDeUso.CORPORATIVO).agora();

		when(pessoaService.findById(dto.getPessoaId(), "Id da Pessoa")).thenReturn(pessoa);

		EmailDTO resp = service.addEmailParaPessoa(dto);

		assertAll(() -> assertEquals(dto.getEmail(), resp.getEmail()),
				() -> assertEquals(dto.getTipoDeUso(), resp.getTipoDeUso()));
	}

	@Test
	@DisplayName("Não deve adicionar Email para a pessoa se já existe")
	void naoDeveAdicionarEmailParaPessoaSeJaEstaCadastrado() {
		EmailDTO dto = CreateEmailDto.get(null, "email@email.com", TipoDeUso.PARTICULAR, ID_PESSOA);
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA")
				.comEmail("email@email.com", TipoDeUso.CORPORATIVO).agora();

		when(pessoaService.findById(dto.getPessoaId(), "Id da Pessoa")).thenReturn(pessoa);

		String msgException = "Email já cadastrado para a pessoa.";
		when(msg.get("email.cadastrado.para.pessoa")).thenReturn(msgException);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.addEmailParaPessoa(dto));
		assertEquals(msgException, exception.getMessage());

	}

}
