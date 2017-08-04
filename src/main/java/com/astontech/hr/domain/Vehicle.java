package com.astontech.hr.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicleId")
    private Integer id;

    private String plate;
    private String vin;
    private Integer year;
    private String color;

    //@JoinColumn(name = "vehicleModel")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleModel vehicleModel;

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

    public Vehicle(String plate, String vin, Integer year, String color, VehicleModel vehicleModel) {
        this.plate = plate;
        this.vin = vin;
        this.year = year;
        this.color = color;
        this.vehicleModel = vehicleModel;
    }

    //endregion

    //region GETTERS/SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    //endregion
}
