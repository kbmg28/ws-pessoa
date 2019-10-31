package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.service.PessoaFisicaService;

@RestController(value = "/pessoaFisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService service;

	@GetMapping("/findByCpf")
	public PessoaFisica findByCpf(@Valid @RequestParam String cpf) {
		return service.findByCpf(cpf);
	}
}