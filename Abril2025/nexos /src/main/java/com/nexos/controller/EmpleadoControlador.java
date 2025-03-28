package com.nexos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexos.modelos.Empleado;
import com.nexos.servicio.EmpleadoServicio;

@Controller
@RequestMapping("/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    // Listar todos los empleados
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Empleado> empleados = empleadoServicio.obtenerTodos();
        model.addAttribute("empleados", empleados);
        return "empleado/listar"; // Thymeleaf -> empleado/listar.html
    }

    // Mostrar formulario para crear un empleado
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleado/crear"; // Thymeleaf -> empleado/crear.html
    }
        @PostMapping("/crear")
    public String guardarDepartamento(@ModelAttribute Empleado empleado) {
        empleadoServicio.guardar(empleado);
        return "redirect:/empleado/listar";
    }

    // Mostrar formulario para editar un empleado
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoServicio.obtenerPorId(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            return "empleado/editar"; // Thymeleaf -> empleado/editar.html
        }
        return "redirect:/empleado/listar";
    }

    // Confirmar eliminación de un empleado
    @GetMapping("/eliminar/{id}")
    public String mostrarConfirmacionEliminar(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoServicio.obtenerPorId(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            return "empleado/eliminar"; // Thymeleaf -> empleado/eliminar.html
        }
        return "redirect:/empleado/listar";
    }
}
