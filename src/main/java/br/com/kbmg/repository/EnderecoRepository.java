package br.com.kbmg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	Optional<List<Endereco>> findByPessoa(Pessoa p);

	
}