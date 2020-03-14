package com.eleads.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
public class LeadDto extends GenericDto<Integer> implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private List<CarDto> cars = new ArrayList<>();

    // constructors
    public LeadDto() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "id=" + id + ", first name=" + firstName + ", last name=" + lastName;
    }
}
