package com.enrique.server.seguridad.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "acronym", length = 100)
    private String acronym;

    @Column(name = "value")
    private int value;

    @JsonIgnore
    @ManyToOne
    private Charateristics charateristics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Charateristics getCharateristics() {
        return charateristics;
    }

    public void setCharateristics(Charateristics charateristics) {
        this.charateristics = charateristics;
    }
}
