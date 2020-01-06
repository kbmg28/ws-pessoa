package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.response.ErrorResponse;
import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.EnderecoService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@PostMapping
	public ResponseEntity<ObjectResponse> addEnderecoParaPessoa(@Valid @RequestBody EnderecoDto enderecoDto,
			BindingResult result) {
		ObjectResponse response = new ObjectResponse();

		if (result.hasErrors()) {
			response.setErrorDescription(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Dados inválidos."));
			
			result.getAllErrors().forEach(error -> response.getErrorDescription().getErrors()
					.add(error.getObjectName() + ": " + error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(service.addEnderecoParaPessoa(enderecoDto));
		return ResponseEntity.ok(response);
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