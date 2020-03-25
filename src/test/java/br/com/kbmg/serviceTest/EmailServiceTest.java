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
import br.com.kbmg.dto.body.EmailBodyDto;
import br.com.kbmg.enums.TipoDeUsoEnum;
import br.com.kbmg.factoryTest.body.dto.CreateEmailBodyDto;
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
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), null)
				.comEmail("teste1@teste.com", TipoDeUsoEnum.PARTICULAR)
				.comEmail("teste2@teste.com", TipoDeUsoEnum.OUTROS).agora();
		List<Email> list = new ArrayList<>(pessoa.getEmails());

		when(repository.findByPessoa(pessoa)).thenReturn(Optional.of(list));
		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

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
		EmailBodyDto dto = CreateEmailBodyDto.get("add@email.com", TipoDeUsoEnum.PARTICULAR);
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA")
				.comEmail("email@email.com", TipoDeUsoEnum.CORPORATIVO).agora();

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		EmailDTO resp = service.addEmailParaPessoa(ID_PESSOA, dto);

		assertAll(() -> assertEquals(dto.getEmail(), resp.getEmail()),
				() -> assertEquals(dto.getTipoDeUso(), resp.getTipoDeUso()));
	}

	@Test
	@DisplayName("Não deve adicionar Email para a pessoa se já existe")
	void naoDeveAdicionarEmailParaPessoaSeJaEstaCadastrado() {
		EmailBodyDto dto = CreateEmailBodyDto.get("email@email.com", TipoDeUsoEnum.PARTICULAR);
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA")
				.comEmail("email@email.com", TipoDeUsoEnum.CORPORATIVO).agora();

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		String msgException = "Email já cadastrado para a pessoa.";
		when(msg.get("email.cadastrado.para.pessoa")).thenReturn(msgException);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.addEmailParaPessoa(ID_PESSOA, dto));
		assertEquals(msgException, exception.getMessage());

	}

}
