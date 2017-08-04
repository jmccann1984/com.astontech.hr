package com.astontech.hr.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicleMakeId")
    private Integer id;

    @Version
    private Integer version;

    private String vehicleMakeName;

//    @JoinColumn(name = "vehicleMake")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicleMake")
    private List<VehicleModel> vehicleModelList;


    //region CONSTRUCTORS
    public VehicleMake() {
    }

    public VehicleMake(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }

    public VehicleMake(String vehicleMakeName, List<VehicleModel> vehicleModelList) {
        this.vehicleMakeName = vehicleMakeName;
        this.vehicleModelList = vehicleModelList;
    }

    //endregion

    //region GETTERS/SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVehicleMakeName() {
        return vehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }


    //endregion
}
