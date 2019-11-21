package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.response.ObjectResponse;
import br.com.kbmg.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping("/findOne")
	public ResponseEntity<ObjectResponse> findOne(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPessoa, "Id da pessoa")));
	}

	@PostMapping
	public ResponseEntity<ObjectResponse> create(@Valid @RequestBody Pessoa pessoa, BindingResult result) {
		ObjectResponse response = new ObjectResponse();

		if (result.hasErrors()) {

			result.getAllErrors().forEach(
					error -> response.getErrors().add(error.getObjectName() + ": " + error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(service.create(pessoa));
		return ResponseEntity.ok(response);
	}
	

	@DeleteMapping
	public void deleteById(@Valid @RequestParam Long idPessoa) {
		service.deleteById(idPessoa);
	}

	@DeleteMapping("/entity")
	public void delete(@Valid @RequestBody Pessoa p) {
		service.delete(p);
	}
}