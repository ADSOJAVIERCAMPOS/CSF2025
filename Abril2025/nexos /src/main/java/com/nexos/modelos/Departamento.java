package com.nexos.modelos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departamento_codigo", nullable = false, unique = true, length = 50)
    private String departamentoCodigo;

    @Column(name = "departamento_nombre", nullable = false, length = 100)
    private String departamentoNombre;

    @Column(name = "fecha_hora_crea", updatable = false, nullable = false)
    private LocalDateTime fechaHoraCrea;

    @Column(name = "fecha_hora_modifica")
    private LocalDateTime fechaHoraModifica;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Empleado> empleados;

    // 🔹 Constructores
    public Departamento() {
        this.fechaHoraCrea = LocalDateTime.now(); // Inicializa la fecha de creación
    }

    public Departamento(String departamentoCodigo, String departamentoNombre) {
        this.departamentoCodigo = departamentoCodigo;
        this.departamentoNombre = departamentoNombre;
        this.fechaHoraCrea = LocalDateTime.now(); // Asegura que la fecha se establezca al crear un objeto
    }

    // 🔹 Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamentoCodigo() {
        return departamentoCodigo;
    }

    public void setDepartamentoCodigo(String departamentoCodigo) {
        this.departamentoCodigo = departamentoCodigo;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    public LocalDateTime getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(LocalDateTime fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public LocalDateTime getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(LocalDateTime fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    // 🔹 Métodos automáticos de persistencia
    @PrePersist
    protected void prePersist() {
        if (this.fechaHoraCrea == null) {
            this.fechaHoraCrea = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.fechaHoraModifica = LocalDateTime.now();
    }
}



