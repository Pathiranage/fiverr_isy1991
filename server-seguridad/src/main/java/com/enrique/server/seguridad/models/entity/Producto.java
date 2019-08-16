package com.enrique.server.seguridad.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String version;
    // Una empresa puede tener varios productos, y un producto pertenece unicamente
    // a una empresa
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "empresa_id")
    @JsonIgnoreProperties({"productos", "hibernateLazyInitializer", "handler"})
    private Empresa empresa;
    // Un producto puede tener una o varias versiones, y la version solo pertenece
    // al producto
    @JsonIgnoreProperties({"producto", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Version> versiones;
    // Un producto puede tener una o varias evaluaciones, y una evaluacion pertenece
    // a un unico producto
    @JsonIgnoreProperties({"productos", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private List<Evaluacion> evaluaciones;

    public Producto() {
        this.versiones = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    public String getNombre() {

        return nombre;

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public String getVersion() {

        return version;

    }

    public void setVersion(String version) {

        this.version = version;

    }

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public Empresa getEmpresa() {

        return empresa;

    }

    public void setEmpresa(Empresa empresa) {

        this.empresa = empresa;

    }

    public List<Version> getVersiones() {

        return versiones;

    }

    public void setVersiones(List<Version> versiones) {

        this.versiones = versiones;

    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}