package com.enrique.server.seguridad.models.entity.Evaluvation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kalana Weerarathne on 30th 06, 2019
 */
@Entity
@Table(name = "macrocharacteristic")
@JsonRootName(value = "macrocharacteristic")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Macrocharacteristic {
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "macrocharacteristic", cascade = CascadeType.ALL)
    private List<Charateristics> charateristics;

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

    public List<Charateristics> getCharateristics() {
        return charateristics;
    }

    public void setCharateristics(List<Charateristics> charateristics) {
        this.charateristics = charateristics;
    }
}
