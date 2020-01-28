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
import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.dto.body.EnderecoBodyDto;
import br.com.kbmg.factoryTest.dto.CreateEnderecoBodyDto;
import br.com.kbmg.repository.EnderecoRepository;
import br.com.kbmg.service.PessoaService;
import br.com.kbmg.service.impl.EnderecoServiceImpl;
import br.com.kbmg.utils.Util;

public class EnderecoServiceTest {

	@InjectMocks
	EnderecoServiceImpl service;

	@Mock
	EnderecoRepository repository;

	@Mock
	PessoaService pessoaService;

	@Mock
	MessagesService msg;

	int i;
	private static final String ID_PESSOA = "1";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Busca todos os endereços da pessoa por ID_PESSOA")
	void deveBuscarEnderecosComIdDaPessoa() {

		Pessoa pessoa = new Pessoa(ID_PESSOA);
		List<Endereco> list = new ArrayList<>(PessoaBuilder.umaPessoa(pessoa.getIdPessoa(), null).comEnderecoFiscal()
				.comEnderecoOutros().agora().getEnderecos());

		when(repository.findByPessoa(pessoa)).thenReturn(Optional.of(list));

		List<?> retorno = service.findByPessoa(ID_PESSOA);

		assertEquals(list.size(), retorno.size());

		for (i = 0; i < retorno.size(); i++) {
			EnderecoDto dto = (EnderecoDto) retorno.get(i);

			assertAll(() -> assertEquals(list.get(i).getLogradouro(), dto.getLogradouro()),
					() -> assertEquals(list.get(i).getNumero(), dto.getNumero()),
					() -> assertEquals(list.get(i).getBairro(), dto.getBairro()));
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

	@Test
	@DisplayName("Deve adicionar Endereco para a pessoa")
	void deveAdicionarEnderecoParaPessoa() {
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA").comEnderecoFiscal()
				.comEnderecoOutros().agora();
		EnderecoBodyDto enderecoBodyDto = CreateEnderecoBodyDto.get("NOVA RUA", "BAIRRO DO TESTE", "1A");

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);
		EnderecoDto retorno = service.addEnderecoParaPessoa(ID_PESSOA, enderecoBodyDto);

		assertAll(() -> assertEquals(enderecoBodyDto.getLogradouro(), retorno.getLogradouro()),
				() -> assertEquals(enderecoBodyDto.getNumero(), retorno.getNumero()),
				() -> assertEquals(enderecoBodyDto.getBairro(), retorno.getBairro()));
	}

	@Test
	@DisplayName("Não deve adicionar Endereco para a pessoa se já existe")
	void naoDeveAdicionarEnderecoParaPessoaSeJaEstaCadastrado() {
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA").comEnderecoFiscal().agora();
		EnderecoBodyDto body = (EnderecoBodyDto) Util.convertObject(pessoa.getEnderecos().stream().findFirst().get(),
				EnderecoBodyDto.class);

		when(pessoaService.findById(ID_PESSOA)).thenReturn(pessoa);

		String msgException = "Endereço já cadastrado para a pessoa.";
		when(msg.get("endereco.cadastrado.para.pessoa")).thenReturn(msgException);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.addEnderecoParaPessoa(ID_PESSOA, body));
		assertEquals(msgException, exception.getMessage());

	}

}
