package com.eleads.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
@Entity
@Table(name = "lead")
public class Lead {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinTable(name = "lead_car",
            joinColumns = @JoinColumn(name = "lead_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars = new ArrayList<>();

    // constructors
    public Lead() {
    }

    public Lead(Integer id) {
        super();
        this.id = id;
    }

    public Lead(Integer id, String firstName, String lastName, String city, List<Car> cars) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.cars = cars;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "id=" + id + ", first name=" + firstName + ", last name=" + lastName;
    }
}
