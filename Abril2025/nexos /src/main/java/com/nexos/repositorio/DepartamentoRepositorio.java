package com.nexos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexos.modelos.Departamento;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByDepartamentoCodigo(String departamentoCodigo);

    Optional<Departamento> findByDepartamentoNombre(String departamentoNombre);

    boolean existsByDepartamentoCodigo(String departamentoCodigo);

}
