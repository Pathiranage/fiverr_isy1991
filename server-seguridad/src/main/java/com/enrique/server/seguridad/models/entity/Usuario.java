package com.enrique.server.seguridad.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 4, max = 20, message = "Tamaño entre 4 y 20")
    @Column(nullable = false)
    private String nombre;
    @NotEmpty(message = "no puede estar vacio")
    private String apellido;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 80)
    private String password;
    private Boolean enabled;
    //Relacion con los Roles
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Rol> roles;
    //Relacion con las Evaluaciones, un usuario puede tener varias evaluaciones, y una evaluacion puede estar asignada a varios usuarios
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_evaluaciones", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "evaluacion_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = {"usuario_id", "evaluacion_id"})})
    private List<Evaluacion> evaluaciones;


    public Usuario() {
        this.evaluaciones = new ArrayList<>();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}
