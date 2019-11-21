package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.domain.PessoaFisica;
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
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPf, "Id da pessoa física")));
	}

	@PutMapping
	public ResponseEntity<ObjectResponse> update(@Valid @RequestBody PessoaFisica pf) {
		return ResponseEntity.ok(new ObjectResponse( service.update(pf, pf.getIdPf())) );
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

	@GetMapping("/paginated")
	public ResponseEntity<ObjectResponse> findAllPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ASC") Sort.Direction direction,
			@RequestParam(defaultValue = "idPf") String sortProperty) {
		return ResponseEntity.ok(new ObjectResponse (service.findAllPaginated(page, size, direction, sortProperty)) );
	}
}