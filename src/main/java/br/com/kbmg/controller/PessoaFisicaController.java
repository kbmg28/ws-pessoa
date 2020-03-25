package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.PessoaFisicaService;

@RestController
@RequestMapping(value = "/pessoaFisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService service;

	@GetMapping("/cpf")
	public ResponseEntity<ObjectResponse> findByCpf(@Valid @RequestParam String cpf) {
		return ResponseEntity.ok(new ObjectResponse(service.findByCpf(cpf)));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idPf) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPf)));
	}

//	@PutMapping
//	public ResponseEntity<ObjectResponse> update(@Valid @RequestBody PessoaFisica pf) {
//		return ResponseEntity.ok(new ObjectResponse( service.update(pf, pf.getIdPf())) );
//	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}
}