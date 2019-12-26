package br.com.kbmg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	Optional<List<Telefone>> findByPessoa(Pessoa p);

	
}