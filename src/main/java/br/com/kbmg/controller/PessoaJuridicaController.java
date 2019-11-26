package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.PessoaJuridicaService;

@RestController
@RequestMapping(value = "/pessoaJuridica")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService service;

	@GetMapping("/cnpj")
	public ResponseEntity<ObjectResponse> findByCnpj(@Valid @RequestParam String cnpj) {
		return ResponseEntity.ok(new ObjectResponse(service.findByCnpj(cnpj)));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idPj) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPj, "Id da pessoa jurídica")));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

}