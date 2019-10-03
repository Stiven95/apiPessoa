package com.oracle.consulting.workshop.repository;

import com.oracle.consulting.workshop.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> { }
