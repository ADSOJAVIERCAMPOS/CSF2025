package com.nexos.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexos.modelos.Empleado;


@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

    @Override
    List<Empleado> findAll();

    @Override
    Optional<Empleado> findById(Long id);

    @Override
    Empleado save(Empleado empleado);
}

