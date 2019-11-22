package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.EnderecoService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;


	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idEndereco) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idEndereco, "Id do endereco")));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

}