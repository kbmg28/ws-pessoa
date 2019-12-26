package br.com.kbmg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.domain.Email;
import br.com.kbmg.domain.Pessoa;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{

	Optional<List<Email>> findByPessoa(Pessoa p);

	
}