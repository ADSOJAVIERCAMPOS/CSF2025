package com.nexos.controller;

import com.nexos.modelos.Departamento;
import com.nexos.modelos.Empleado;
import com.nexos.servicio.DepartamentoServicio;
import com.nexos.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;
    @Autowired
    private DepartamentoServicio departamentoServicio;

    // Listar empleados
    @GetMapping("/listar")
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoServicio.obtenerTodos());
        return "empleado/listar"; // Thymeleaf -> templates/empleados/listar.html
    }

    // Mostrar formulario para crear un empleado
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
         List<Departamento> departamentoList = departamentoServicio.obtenerTodos();
        model.addAttribute("departamentos", departamentoList);
        model.addAttribute("empleado", new Empleado());
        return "empleado/crear"; // Thymeleaf -> templates/empleados/crear.html
    }

    // Guardar empleado
    @PostMapping("/crear")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {

        Departamento departamento = departamentoServicio.obtenerPorId(empleado.getDepartamento().getId()).orElseThrow();
        empleado.setDepartamento(departamento);
        empleadoServicio.guardar(empleado);
        return "redirect:/empleado/listar";
    }

    // Mostrar formulario para editar un empleado
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoServicio.obtenerPorId(id);
        if (empleado.isPresent()) {
            List<Departamento> departamentoList = departamentoServicio.obtenerTodos();
            model.addAttribute("departamentos", departamentoList);
            model.addAttribute("empleado", empleado.get());
            return "empleado/editar"; // Thymeleaf -> templates/empleados/editar.html
        }
        return "redirect:/empleado/listar";
    }

    // Actualizar empleado
    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoServicio.obtenerPorId(id);

        if (empleadoExistente.isPresent()) {
            Empleado emp = empleadoExistente.get();
            emp.setDocumentoTipo(empleado.getDocumentoTipo());
            emp.setDocumentoNumero(empleado.getDocumentoNumero());
            emp.setNombres(empleado.getNombres());
            emp.setApellidos(empleado.getApellidos());

            // 🔥 Verificar y asignar el departamento correctamente
            if (empleado.getDepartamento() != null && empleado.getDepartamento().getId() != null) {
                Departamento departamento = departamentoServicio.obtenerPorId(empleado.getDepartamento().getId())
                        .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                emp.setDepartamento(departamento);
            }
            // 🔥 Verificar si ciudad es null y asignar un valor predeterminado
            if (empleado.getCiudad() != null && !empleado.getCiudad().isEmpty()) {
                emp.setCiudad(empleado.getCiudad());
            } else {
                emp.setCiudad("Ciudad Desconocida"); // Cambia esto según tus necesidades
            }

            // 🔥 Verificar que dirección no sea nula
            if (empleado.getDireccion() != null && !empleado.getDireccion().isEmpty()) {
                emp.setDireccion(empleado.getDireccion());
            } else {
                emp.setDireccion("Dirección no especificada"); // Valor por defecto
            }


            emp.setCorreoElectronico(empleado.getCorreoElectronico());
            emp.setTelefono(empleado.getTelefono());
            emp.setFechaHoraModifica(java.time.LocalDateTime.now());

            // Guardar los cambios
            empleadoServicio.actualizar(id, emp);
        }
        return "redirect:/empleado/listar";
    }


    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoServicio.eliminar(id);
        return "redirect:/empleado/listar";
    }
}

