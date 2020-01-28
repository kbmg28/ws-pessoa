package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.dto.body.EnderecoBodyDto;
import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.EnderecoService;
import br.com.kbmg.utils.Util;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@PostMapping
	public ResponseEntity<ObjectResponse> addEnderecoParaPessoa(@RequestParam String idPessoa,
			@Valid @RequestBody EnderecoBodyDto body, BindingResult result) {

		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.addEnderecoParaPessoa(idPessoa, body));
	}

	@PutMapping
	public ResponseEntity<ObjectResponse> update(@RequestParam String idEndereco,
			@Valid @RequestBody EnderecoBodyDto body, BindingResult result) {
		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.update(idEndereco, body, EnderecoDto.class));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idEndereco) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idEndereco, "Id do endereco", EnderecoDto.class)));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAllDto(EnderecoDto.class)));
	}

	@GetMapping("/allByPessoa")
	public ResponseEntity<ObjectResponse> findAllByPessoa(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findByPessoa(idPessoa)));
	}

}