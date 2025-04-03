package com.nexos.servicio;

import com.nexos.modelos.Empleado;
import com.nexos.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    // Obtener todos los empleados
    public List<Empleado> obtenerTodos() {
        return empleadoRepositorio.findAll();
    }

    // Obtener empleado por ID
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepositorio.findById(id);
    }

    // Guardar nuevo empleado
    public void guardar(Empleado empleado) {
        empleado.setFechaHoraCrea(LocalDateTime.now());
        empleado.setFechaHoraModifica(LocalDateTime.now());
        empleadoRepositorio.save(empleado);
    }

    // Actualizar empleado existente
    public void actualizar(Long id, Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoRepositorio.findById(id);
        if (empleadoExistente.isPresent()) {
            Empleado emp = empleadoExistente.get();
            emp.setDocumentoTipo(empleado.getDocumentoTipo());
            emp.setDocumentoNumero(empleado.getDocumentoNumero());
            emp.setNombres(empleado.getNombres());
            emp.setApellidos(empleado.getApellidos());
            emp.setDepartamento(empleado.getDepartamento());
            emp.setCiudad(empleado.getCiudad());
            emp.setDireccion(empleado.getDireccion());
            emp.setCorreoElectronico(empleado.getCorreoElectronico());
            emp.setTelefono(empleado.getTelefono());
            emp.setFechaHoraModifica(LocalDateTime.now());
            empleadoRepositorio.save(emp);
        }
    }

    // Eliminar empleado
    public void eliminar(Long id) {
        empleadoRepositorio.deleteById(id);
    }
}
