package com.enrique.server.seguridad.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "atributos")
public class Atributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
