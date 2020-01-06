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
		Endereco endereco = pessoa.getEnderecos().stream().findFirst().get();
		EnderecoDto dto = (EnderecoDto) Util.convertObject(endereco, EnderecoDto.class);

		pessoa.getEnderecos().remove(endereco);

		when(pessoaService.findById(dto.getPessoaId(), "Id da Pessoa")).thenReturn(pessoa);
		EnderecoDto retorno = service.addEnderecoParaPessoa(dto);

		assertAll(() -> assertEquals(dto.getLogradouro(), retorno.getLogradouro()),
				() -> assertEquals(dto.getNumero(), retorno.getNumero()),
				() -> assertEquals(dto.getBairro(), retorno.getBairro()));
	}

	@Test
	@DisplayName("Não deve adicionar Endereco para a pessoa se já existe")
	void naoDeveAdicionarEnderecoParaPessoaSeJaEstaCadastrado() {
		Pessoa pessoa = PessoaBuilder.umaPessoa(Long.parseLong(ID_PESSOA), "PESSOA").comEnderecoFiscal().agora();
		EnderecoDto dto = (EnderecoDto) Util.convertObject(pessoa.getEnderecos().stream().findFirst().get(),
				EnderecoDto.class);
		
		when(pessoaService.findById(dto.getPessoaId(), "Id da Pessoa")).thenReturn(pessoa);

		String msgException = "Endereço já cadastrado para a pessoa.";
		when(msg.get("endereco.cadastrado.para.pessoa")).thenReturn(msgException);

		InvalidParameterException exception = assertThrows(InvalidParameterException.class,
				() -> service.addEnderecoParaPessoa(dto));
		assertEquals(msgException, exception.getMessage());

	}

}
