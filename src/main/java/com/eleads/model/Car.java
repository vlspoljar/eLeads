package com.eleads.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_doors")
    private Integer numDoors;

    @Column(name = "color")
    private String color;

    @JoinColumn(name = "engine_type_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EngineType engineTypeId;

    @JoinColumn(name = "car_brand_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CarBrand carBrandId;

    @JoinColumn(name = "car_model_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CarModel carModelId;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinTable(name = "lead_car",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "lead_id")
    )
    private List<Lead> leads = new ArrayList<>();

    // constructors
    public Car() {
    }

    public Car(Integer id) {
        super();
        this.id = id;
    }

    public Car(Integer id, Integer numDoors, String color, EngineType engineTypeId, CarBrand carBrandId, CarModel carModelId, List<Lead> leads) {
        super();
        this.id = id;
        this.numDoors = numDoors;
        this.color = color;
        this.engineTypeId = engineTypeId;
        this.carBrandId = carBrandId;
        this.carModelId = carModelId;
        this.leads = leads;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

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

    public EngineType getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(EngineType engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public CarBrand getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(CarBrand carBrandId) {
        this.carBrandId = carBrandId;
    }

    public CarModel getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(CarModel carModelId) {
        this.carModelId = carModelId;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    @Override
    public String toString() {
        return "id=" + id + ", brand=" + carBrandId.getName() + ", model=" + carModelId.getName();
    }
}
