package com.astontech.hr.domain.VO;

public class VehicleMakeVO {

    private String newVehicleMakeName;
    private String newVehicleModels;
    private String[] newVehicleModelArray;


    public VehicleMakeVO() {}

    public void SplitVehicleModelsToArray() {
        this.setNewVehicleModelArray(this.newVehicleModels.split("\\r?\\n"));
    }

    public String getNewVehicleMakeName() {
        return newVehicleMakeName;
    }

    public void setNewVehicleMakeName(String newVehicleMakeName) {
        this.newVehicleMakeName = newVehicleMakeName;
    }

    public String getNewVehicleModels() {
        return newVehicleModels;
    }

    public void setNewVehicleModels(String newVehicleModels) {
        this.newVehicleModels = newVehicleModels;
    }

    public String[] getNewVehicleModelArray() {
        return newVehicleModelArray;
    }

    public void setNewVehicleModelArray(String[] newVehicleModelArray) {
        this.newVehicleModelArray = newVehicleModelArray;
    }
}