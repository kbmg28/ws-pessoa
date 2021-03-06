package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.TelefoneService;

@RestController
@RequestMapping(value = "/telefone")
public class TelefoneController {

	@Autowired
	private TelefoneService service;


	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idEndereco) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idEndereco)));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

	@GetMapping("/allByPessoa")
	public ResponseEntity<ObjectResponse> findAllByPessoa(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findByPessoa(idPessoa)));
	}

}