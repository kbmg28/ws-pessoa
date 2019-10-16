package br.com.kbmg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbmg.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}