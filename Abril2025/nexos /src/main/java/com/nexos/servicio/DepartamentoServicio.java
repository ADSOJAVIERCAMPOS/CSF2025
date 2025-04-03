package com.nexos.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.modelos.Departamento;
import com.nexos.repositorio.DepartamentoRepositorio;

@Service
public class DepartamentoServicio {

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    // Listar todos los departamentos
    public List<Departamento> obtenerTodos() {
        return departamentoRepositorio.findAll();
    }

    // Obtener departamento por ID
    public Optional<Departamento> obtenerPorId(Long id) {
        return departamentoRepositorio.findById(id);
    }

    // Guardar un nuevo departamento
    public Departamento guardar(Departamento departamento) {
        departamento.setFechaHoraCrea(LocalDateTime.now()); // Establece fecha de creación al guardar
        return departamentoRepositorio.save(departamento);
    }

    public void eliminar(Long id) {
        departamentoRepositorio.deleteById(id);
    }

    public Departamento actualizar(Long id, Departamento departamento) {
        Optional<Departamento> departamentoExistente = departamentoRepositorio.findById(id);
        if (departamentoExistente.isPresent()) {
            Departamento dep = departamentoExistente.get();
            dep.setDepartamentoCodigo(departamento.getDepartamentoCodigo());
            dep.setDepartamentoNombre(departamento.getDepartamentoNombre());
            dep.setFechaHoraModifica(LocalDateTime.now());
            return departamentoRepositorio.save(dep);
        }
        throw new RuntimeException("Departamento no encontrado con id: " + id);
    }
}

