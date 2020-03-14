package com.eleads.dto;

import sun.net.www.content.text.Generic;

import java.io.Serializable;

/**
 * Created by Vlatko
 */
public class CarModelDto extends GenericDto<Integer> implements Serializable {

    private Integer id;
    private String name;

    // constructors
    public CarModelDto() {
    }

    public CarModelDto(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // getters and setters
    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
