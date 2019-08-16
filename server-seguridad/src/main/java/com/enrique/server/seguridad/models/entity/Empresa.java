package com.enrique.server.seguridad.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity

@Table(name = "empresas")

public class Empresa implements Serializable {


    /**
     *
     */

    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String direccion;
    private int telefono;
    private String cif;
    private String nombre_empleado;
    private String apellido_empleado;
    private String cargo_empleado;
    private String email_empleado;
    @JsonIgnoreProperties({"empresa", "hibernateLazyInitializer", "handler"})

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.ALL)

    private List<Producto> productos;


    public Empresa() {

        this.productos = new ArrayList<>();

    }

    public String getNombre_empleado() {

        return nombre_empleado;

    }

    public void setNombre_empleado(String nombre_empleado) {

        this.nombre_empleado = nombre_empleado;

    }

    public String getApellido_empleado() {

        return apellido_empleado;

    }

    public void setApellido_empleado(String apellido_empleado) {

        this.apellido_empleado = apellido_empleado;

    }

    public String getCargo_empleado() {

        return cargo_empleado;

    }

    public void setCargo_empleado(String cargo_empleado) {

        this.cargo_empleado = cargo_empleado;

    }

    public String getEmail_empleado() {

        return email_empleado;

    }

    public void setEmail_empleado(String email_empleado) {

        this.email_empleado = email_empleado;

    }

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getNombre() {

        return nombre;

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public String getDireccion() {

        return direccion;

    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;

    }

    public int getTelefono() {

        return telefono;

    }

    public void setTelefono(int telefono) {

        this.telefono = telefono;

    }

    public String getCif() {

        return cif;

    }

    public void setCif(String cif) {

        this.cif = cif;

    }

    public List<Producto> getProductos() {

        return productos;

    }

    public void setProductos(List<Producto> productos) {

        this.productos = productos;

    }



}
