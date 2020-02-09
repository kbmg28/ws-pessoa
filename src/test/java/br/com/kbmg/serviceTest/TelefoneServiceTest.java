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
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.Telefone;
import br.com.kbmg.dto.TelefoneDto;
import br.com.kbmg.dto.body.TelefoneBodyDto;
import br.com.kbmg.factoryTest.body.dto.CreateTelefoneBodyDto;
import br.com.kbmg.repository.TelefoneRepository;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.service.impl.TelefoneServiceImpl;

public class TelefoneServiceTest {

	@InjectMocks
	TelefoneServiceImpl service;

	@Mock
	PessoaService pessoaService;

	@Mock
	TelefoneRepository repository;

	@Mock
	MessagesService msg;

	private static final String ID_PESSOA = "1";
	private static final Integer TELEFONE_1 = 991855864;
	private static final Integer TELEFONE_2 = 999167988;
	int i;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Busca todos os telefones da pessoa por ID_PESSOA")
	void deveBuscarTelefonesComIdDaPessoa() {
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), null).comTelefone(TELEFONE_1)
				.comTelefone(TELEFONE_2).agora();
		List<Telefone> list = new ArrayList<>(pessoa.getTelefones());

		when(repository.findByPessoa(pessoa)).thenReturn(Optional.of(list));
		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		List<?> retorno = service.findByPessoa(ID_PESSOA);

		assertEquals(list.size(), retorno.size());

		for (i = 0; i < retorno.size(); i++) {
			TelefoneDto telefoneDto = (TelefoneDto) retorno.get(i);

			assertAll(() -> assertEquals(list.get(i).getDdd(), telefoneDto.getDdd()),
					() -> assertEquals(list.get(i).getNumero(), telefoneDto.getNumero()));
		}
	}

	@Test
	@DisplayName("Lança exception quando não encontrar telefones de uma Pessoa por ID_PESSOA")
	void deveLancarExceptionSeNaoEncontrarTelefonesPorIdPessoa() {
		String msgException = "Pessoa sem telefones cadastrados.";
		when(msg.get("pessoa.sem.telefones")).thenReturn(msgException);

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
				() -> service.findByPessoa(ID_PESSOA));
		assertEquals(msgException, exception.getMessage());
	}

	@Test
	@DisplayName("Deve adicionar Telefone para a pessoa")
	void deveAdicionarTelefoneParaPessoa() {
		TelefoneBodyDto body = CreateTelefoneBodyDto.get(TELEFONE_1.toString());
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA").comTelefone(TELEFONE_2).agora();

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		TelefoneDto resp = service.addTelefoneParaPessoa(ID_PESSOA, body);

		assertAll(() -> assertEquals(body.getDdd(), resp.getDdd().toString()),
				() -> assertEquals(body.getNumero(), resp.getNumero().toString()));
	}

	@Test
	@DisplayName("Não deve adicionar Telefone para a pessoa se já existe")
	void naoDeveAdicionarTelefoneParaPessoaSeJaEstaCadastrado() {
		TelefoneBodyDto body = CreateTelefoneBodyDto.get(TELEFONE_1.toString());
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA").comTelefone(TELEFONE_1).agora();

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		String msgException = "Telefone já cadastrado para a pessoa.";
		when(msg.get("telefone.cadastrado.para.pessoa")).thenReturn(msgException);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.addTelefoneParaPessoa(ID_PESSOA, body));
		assertEquals(msgException, exception.getMessage());

	}

}
