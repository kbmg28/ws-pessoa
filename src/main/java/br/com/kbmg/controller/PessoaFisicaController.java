package br.com.kbmg.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.service.PessoaFisicaService;

@RestController
@RequestMapping(value = "/pessoaFisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService service;

	@GetMapping("/cpf")
	public PessoaFisica findByCpf(@Valid @RequestParam String cpf) {
		return service.findByCpf(cpf);
	}

	@GetMapping
	public PessoaFisica findById(@Valid @RequestParam String idPf) {
		return service.findById(idPf, "Id da pessoa física");
	}

	@PutMapping
	public PessoaFisica update(@Valid @RequestBody PessoaFisica pf) {
		return service.update(pf, pf.getIdPf());
	}

	@GetMapping("/all")
	public List<PessoaFisica> findAll() {
		return service.findAll();
	}

	@GetMapping("/paginated")
	public Page<PessoaFisica> findAllPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ASC") Sort.Direction direction,
			@RequestParam(defaultValue = "idPf") String sortProperty) {
		return service.findAllPaginated(page, size, direction, sortProperty);
	}

	@DeleteMapping
	public void deleteById(@Valid @RequestParam Long idPf) {
		service.deleteById(idPf);
	}

	@DeleteMapping("/entity")
	public void deleteById2(@Valid @RequestBody PessoaFisica pf) {
		service.delete(pf);
	}
}