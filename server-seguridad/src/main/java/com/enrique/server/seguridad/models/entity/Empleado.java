package com.enrique.server.seguridad.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private String email;

}
