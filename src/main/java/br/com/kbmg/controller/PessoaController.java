package br.com.kbmg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping
	public ResponseEntity<ObjectResponse> update(@Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(new ObjectResponse( service.update(pessoa, pessoa.getIdPessoa())) );
	}

	@GetMapping
	public ResponseEntity<ObjectResponse> findOne(@Valid @RequestParam String idPessoa) {
		return ResponseEntity.ok(new ObjectResponse(service.findById(idPessoa, "Id da pessoa")));
	}

	@GetMapping("/all")
	public ResponseEntity<ObjectResponse> findAll() {
		return ResponseEntity.ok(new ObjectResponse(service.findAll()));
	}

	@GetMapping("/paginated")
	public ResponseEntity<ObjectResponse> findAllPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ASC") Sort.Direction direction,
			@RequestParam(defaultValue = "nomeCompleto") String sortProperty) {
		return ResponseEntity.ok(new ObjectResponse (service.findAllPaginated(page, size, direction, sortProperty)) );
	}
	
	@DeleteMapping("/deleteOne")
	public ResponseEntity<ObjectResponse> deleteById(@Valid @RequestParam Long idPessoa) {
		this.service.deleteById(idPessoa);
		return ResponseEntity.ok(new ObjectResponse("Objeto deletado com sucesso"));
	}

	@DeleteMapping("/deleteEntity")
	public ResponseEntity<ObjectResponse> delete(@Valid @RequestBody Pessoa pessoa) {
		service.delete(pessoa);
		return ResponseEntity.ok(new ObjectResponse("Objeto deletado com sucesso"));
	}
}