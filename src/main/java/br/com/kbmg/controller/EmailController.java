package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.dto.EmailBodyDto;
import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.response.ErrorResponse;
import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.EmailService;
import br.com.kbmg.service.impl.GenericServiceImpl;
import br.com.kbmg.utils.Validator;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

	@Autowired
	private EmailService service;

	@PostMapping
	public ResponseEntity<ObjectResponse> addEmailParaPessoa(@Valid @RequestBody EmailDTO emailDto,
			BindingResult result) {
		ObjectResponse response = new ObjectResponse();

		if (result.hasErrors()) {
			response.setErrorDescription(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Dados inválidos."));
			
			result.getAllErrors().forEach(error -> response.getErrorDescription().getErrors()
					.add(error.getObjectName() + ": " + error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(service.addEmailParaPessoa(emailDto));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idEmail) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idEmail, "Id do email", EmailDTO.class)));
	}

	@PutMapping
	public ResponseEntity<ObjectResponse> update(@Valid @RequestBody EmailBodyDto emailBodyDto,
			BindingResult result) {
//		return Validator.verifyRequestBody(emailBodyDto, result, service::update);
		Validator.verifyRequestBody(emailBodyDto, result, GenericServiceImpl::update);
		return ResponseEntity.ok(new ObjectResponse(service.update(emailBodyDto.getIdEmail(), emailBodyDto, EmailDTO.class)));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAllDto(EmailDTO.class)));
	}

	@GetMapping("/allByPessoa")
	public ResponseEntity<ObjectResponse> findAllByPessoa(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findByPessoa(idPessoa)));
	}

}