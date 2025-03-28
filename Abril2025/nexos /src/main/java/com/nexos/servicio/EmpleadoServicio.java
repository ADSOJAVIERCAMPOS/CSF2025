package com.nexos.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.modelos.Empleado;
import com.nexos.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    // Listar todos los empleados
    public List<Empleado> obtenerTodos() {
        return empleadoRepositorio.findAll();
    }

    // Obtener empleado por ID
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepositorio.findById(id);
    }

    // Guardar un nuevo empleado
    public Empleado guardar(Empleado empleado) {
        empleado.setFechaHoraCrea(LocalDateTime.now());
        empleado.setFechaHoraModifica(LocalDateTime.now());
        return empleadoRepositorio.save(empleado);
    }

    // Actualizar empleado
    public Empleado actualizar(Long id, Empleado empleadoActualizado) {
        return empleadoRepositorio.findById(id).map(empleado -> {
            empleado.setNombres(empleadoActualizado.getNombres());
            empleado.setApellidos(empleadoActualizado.getApellidos());
            empleado.setDocumentoNumero(empleadoActualizado.getDocumentoNumero());
            empleado.setCorreoElectronico(empleadoActualizado.getCorreoElectronico());
            empleado.setTelefono(empleadoActualizado.getTelefono());
            empleado.setDepartamento(empleadoActualizado.getDepartamento());
            empleado.setFechaHoraModifica(LocalDateTime.now());
            return empleadoRepositorio.save(empleado);
        }).orElse(null);
    }

    // Eliminar empleado
    public void eliminar(Long id) {
        empleadoRepositorio.deleteById(id);
    }
}
