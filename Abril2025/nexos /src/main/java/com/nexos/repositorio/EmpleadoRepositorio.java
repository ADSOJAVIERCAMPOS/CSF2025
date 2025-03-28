package com.nexos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexos.modelos.Empleado;


@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
}
