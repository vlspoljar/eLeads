package com.eleads.dto;

import java.io.Serializable;

/**
 * Created by Vlatko
 */
public class CarDto extends GenericDto<Integer> implements Serializable {

    private Integer id;
    private Integer numDoors;
    private String color;
    private EngineTypeDto engineType;
    private CarBrandDto carBrand;
    private CarModelDto carModel;

    // constructors
    public CarDto() {
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

    public Integer getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(Integer numDoors) {
        this.numDoors = numDoors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public EngineTypeDto getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineTypeDto engineType) {
        this.engineType = engineType;
    }

    public CarBrandDto getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandDto carBrand) {
        this.carBrand = carBrand;
    }

    public CarModelDto getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDto carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "id=" + id + ", brand=" + carBrand.getName() + ", model=" + carModel.getName();
    }
}
