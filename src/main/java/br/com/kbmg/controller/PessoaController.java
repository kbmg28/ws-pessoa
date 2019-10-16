package br.com.kbmg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbmg.service.PessoaService;

@RestController(value="/pessoa")
public class PessoaController {
    
	@Autowired
	private PessoaService service;

    @GetMapping()
    public String getVersion() {
        return null;
    }
}