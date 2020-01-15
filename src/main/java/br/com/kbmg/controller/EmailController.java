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

import br.com.kbmg.dto.EmailDTO;
import br.com.kbmg.dto.body.EmailBodyDto;
import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.EmailService;
import br.com.kbmg.utils.Util;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

	@Autowired
	private EmailService service;

	@PostMapping
	public ResponseEntity<ObjectResponse> addEmailParaPessoa(@Valid @RequestBody EmailBodyDto emailDto,
			@RequestParam String idPessoa,
			BindingResult result) {

		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.addEmailParaPessoa(idPessoa, emailDto));
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findById(@Valid @RequestParam String idEmail) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idEmail, "Id do email", EmailDTO.class)));
	}

	@PutMapping
	public ResponseEntity<ObjectResponse> update(@Valid @RequestBody EmailBodyDto emailBodyDto, BindingResult result) {
		return result.hasErrors() ? Util.responseBad(result)
				: Util.createResponseOk(service.update(emailBodyDto.getIdEmail(), emailBodyDto, EmailDTO.class));
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