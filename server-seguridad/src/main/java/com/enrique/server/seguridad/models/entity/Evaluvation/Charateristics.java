package com.enrique.server.seguridad.models.entity.Evaluvation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kalana Weerarathne on 30th 06, 2019
 */
@Entity
@Table(name = "charateristics")
public class Charateristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "acronym", length = 100)
    private String acronym;

    @Column(name = "value")
    private int value;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "charateristics", cascade = CascadeType.PERSIST)
    private List<Properties> properties;

    @ManyToOne
    @JsonIgnore
    private Macrocharacteristic macrocharacteristic;

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

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public Macrocharacteristic getMacrocharacteristic() {
        return macrocharacteristic;
    }

    public void setMacrocharacteristic(Macrocharacteristic macrocharacteristic) {
        this.macrocharacteristic = macrocharacteristic;
    }
}
