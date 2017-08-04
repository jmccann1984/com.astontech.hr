package com.astontech.hr.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicleModelId")
    private Integer id;

    private String vehicleModelName;

//    @JoinColumn(name = "vehicleModel")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicleModel")
    private List<Vehicle> vehicleList;

//    @JoinColumn(name = "vehicleMake", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleMake vehicleMake;

    @Version
    private Integer version;


    //region CONSTRUCTORS
    public VehicleModel() {
    }

    public VehicleModel(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public VehicleModel(String vehicleModelName, VehicleMake vehicleMake) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleMake = vehicleMake;
    }

    public VehicleModel(String vehicleModelName, List<Vehicle> vehicleList) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleList = vehicleList;
    }

    public VehicleModel(String vehicleModelName, List<Vehicle> vehicleList, VehicleMake vehicleMake) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleList = vehicleList;
        this.vehicleMake = vehicleMake;
    }

    //endregion

    //region GETTERS/SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

//endregion
}
