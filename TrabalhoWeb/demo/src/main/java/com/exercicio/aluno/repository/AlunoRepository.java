package com.exercicio.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.aluno.model.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

