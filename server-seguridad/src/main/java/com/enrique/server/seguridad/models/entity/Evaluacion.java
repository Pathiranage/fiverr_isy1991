package com.enrique.server.seguridad.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evaluaciones")
@JsonIgnoreProperties(value = {"usuarios"}, allowSetters = true)
public class Evaluacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "version")
    private String version;

    // Un producto puede tener una o varias evaluaciones, y una evaluacion pertenece
    // a un unico producto
    @JsonIgnoreProperties({"evaluaciones", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @ManyToMany(mappedBy = "evaluaciones", cascade = CascadeType.MERGE)
    private List<Usuario> usuarios;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "macrocharacteristic_id", referencedColumnName = "id")
    private Macrocharacteristic macrocharacteristic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Macrocharacteristic getMacrocharacteristic() {
        return macrocharacteristic;
    }

    public void setMacrocharacteristic(Macrocharacteristic macrocharacteristic) {
        this.macrocharacteristic = macrocharacteristic;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}