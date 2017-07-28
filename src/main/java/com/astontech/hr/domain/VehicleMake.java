package com.astontech.hr.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleMakeId")
    private Integer id;

    @Version
    private Integer version;

    private String vehicleMakeName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VehicleModel> vehicleModelList;


    //region CONSTRUCTORS
    public VehicleMake() {
    }

    public VehicleMake(String vehicleMakeName) {
        vehicleMakeName = vehicleMakeName;
    }

    public VehicleMake(String vehicleMakeName, List<VehicleModel> vehicleModelList) {
        this.vehicleMakeName = vehicleMakeName;
        this.vehicleModelList = vehicleModelList;
    }

    //endregion

    //region GETTERS/SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getVehicleMakeName() {
        return vehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        vehicleMakeName = vehicleMakeName;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }

    //endregion
}
