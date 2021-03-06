package com.eleads.model;

import javax.persistence.*;

/**
 * Created by Vlatko
 */
@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // constructors
    public CarBrand() {
    }

    public CarBrand(Integer id) {
        super();
        this.id = id;
    }

    public CarBrand(Integer id, String name) {
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
