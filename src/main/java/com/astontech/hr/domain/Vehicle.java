package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleId")
    private Integer id;

    private String plate;
    private String vin;
    private Integer year;
    private String color;

    @Version
    private Integer version;

    //region CONSTRUCTORS
    public Vehicle() {
    }

    public Vehicle(String plate, String vin, Integer year, String color) {
        this.plate = plate;
        this.vin = vin;
        this.year = year;
        this.color = color;
    }

    //endregion

    //region GETTERS/SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //endregion
}
