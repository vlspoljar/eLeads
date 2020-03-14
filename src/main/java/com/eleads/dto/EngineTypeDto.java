package com.eleads.dto;

import java.io.Serializable;

/**
 * Created by Vlatko
 */
public class EngineTypeDto extends GenericDto<Integer> implements Serializable {

    private Integer id;
    private String name;

    // constructors
    public EngineTypeDto() {
    }

    public EngineTypeDto(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // getters and setters
    @Override
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
