package com.nexos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexos.modelos.Departamento;
import com.nexos.servicio.DepartamentoServicio;

@Controller
@RequestMapping("/departamento")
public class DepartamentoControlador {

    @Autowired
    private DepartamentoServicio departamentoServicio;

    // Listar todos los departamentos
    @GetMapping("/listar")
    public String listarDepartamentos(Model model) {
        List<Departamento> departamentos = departamentoServicio.obtenerTodos();
        model.addAttribute("departamentos", departamentos); // Ahora en plural
        return "departamento/listar"; // Retorna a la vista listar.html en templates/departamento/
    }

    // Mostrar formulario para crear un nuevo departamento
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "departamento/crear"; // Retorna a la vista crear.html en templates/departamentos/
    }

    // Guardar nuevo departamento
    @PostMapping("/crear")
    public String guardarDepartamento(@ModelAttribute Departamento departamento) {
        departamentoServicio.guardar(departamento);
        return "redirect:/departamento/listar";
    }
    // Mostrar formulario para editar un departamento
@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Departamento> departamento = departamentoServicio.obtenerPorId(id);
        if (departamento.isPresent()) {
            model.addAttribute("departamento", departamento.get());
            return "departamento/editar"; // Vista editar.html en templates/departamento/
        } else {
            return "redirect:/departamento/listar";
        }
    }
    // Actualizar un departamento
    @PostMapping("/editar/{id}")
    public String actualizarDepartamento(@PathVariable Long id, @ModelAttribute Departamento departamento) {
        Optional<Departamento> departamentoExistente = departamentoServicio.obtenerPorId(id);
        if (departamentoExistente.isPresent()) {
            Departamento dep = departamentoExistente.get();
            dep.setDepartamentoCodigo(departamento.getDepartamentoCodigo());
            dep.setDepartamentoNombre(departamento.getDepartamentoNombre());
            dep.setFechaHoraModifica(LocalDateTime.now()); // Actualiza la fecha de modificación
            departamentoServicio.actualizar(id, dep);
        }
        return "redirect:/departamento/listar";
    }
    // Eliminar un departamento
    @GetMapping("/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Long id) {
        departamentoServicio.eliminar(id);
        return "redirect:/departamento/listar";
    }
}
