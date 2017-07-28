package com.astontech.hr.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleModelId")
    private Integer id;

    private String vehicleModelName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> vehicleList;

    @Version
    private Integer version;


    //region CONSTRUCTORS
    public VehicleModel() {
    }

    public VehicleModel(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public VehicleModel(String vehicleModelName, List<Vehicle> vehicleList) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleList = vehicleList;
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

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }


    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    //endregion
}
