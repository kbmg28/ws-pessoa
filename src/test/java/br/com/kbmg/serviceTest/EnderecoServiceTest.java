package br.com.kbmg.serviceTest;

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
import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.repository.EnderecoRepository;
import br.com.kbmg.service.impl.EnderecoServiceImpl;

public class EnderecoServiceTest {

	@InjectMocks
	EnderecoServiceImpl service;

	@Mock
	EnderecoRepository repository;

	@Mock
	MessagesService msg;

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

		List<Endereco> retorno = service.findByPessoa(ID_PESSOA);

		assertEquals(list.size(), retorno.size());

		for (int i = 0; i < retorno.size(); i++) {
			assertEquals(list.get(i).getIdEndereco(), retorno.get(i).getIdEndereco());
			assertEquals(list.get(i).getCep(), retorno.get(i).getCep());
			assertEquals(list.get(i).getNumero(), retorno.get(i).getNumero());
			assertEquals(list.get(i).getTipoEndereco(), retorno.get(i).getTipoEndereco());
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
