package br.com.kbmg.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
	MessagesService msgService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("juivbs")
	void test (){
		Pessoa p = CreatePessoa.getFisica(1L, "TEST JUNIT5", "70715922092");
		
//		when(repository.findById(p.getId_pessoa())).thenReturn(Optional.of(p));
		when(repository.findById(20L)).thenReturn(Optional.empty());
		Pessoa retorno = service.findByCodPessoa(1L);
		
		assertEquals(p.getNomeCompleto(), retorno.getNomeCompleto());
	}

}
