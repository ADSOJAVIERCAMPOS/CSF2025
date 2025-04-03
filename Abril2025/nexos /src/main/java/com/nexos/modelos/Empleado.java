package com.nexos.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String documentoTipo; // RC, TI, CC, CE

    @Column(nullable = false, unique = true, length = 20)
    private String documentoNumero;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String correoElectronico;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaHoraCrea;

    @Column(nullable = false)
    private LocalDateTime fechaHoraModifica;

    // Constructor vacío
    public Empleado() {}

    // Constructor con parámetros
    public Empleado(String documentoTipo, String documentoNumero, String nombres, String apellidos,
                    Departamento departamento, String ciudad, String direccion,
                    String correoElectronico, String telefono) {
        this.documentoTipo = documentoTipo;
        this.documentoNumero = documentoNumero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.fechaHoraCrea = LocalDateTime.now();
        this.fechaHoraModifica = LocalDateTime.now();
    }

    // Métodos para inicializar fechas antes de guardar en la BD
    @PrePersist
    protected void onCreate() {
        this.fechaHoraCrea = LocalDateTime.now();
        this.fechaHoraModifica = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaHoraModifica = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
}

