package com.eleads.model;

import javax.persistence.*;

/**
 * Created by Vlatko
 */
@Entity
@Table(name = "car_model")
public class CarModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // constructors
    public CarModel() {
    }

    public CarModel(Integer id) {
        super();
        this.id = id;
    }

    public CarModel(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }

}
