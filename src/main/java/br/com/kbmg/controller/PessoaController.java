package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping("/findOne")
	public Pessoa findOne(@Valid @RequestParam String id_pessoa) {
		return service.findByIdPessoa(id_pessoa);
	}

	@PostMapping
	public Pessoa create(@Valid @RequestBody Pessoa pessoa) {
		return service.create(pessoa);
	}
}