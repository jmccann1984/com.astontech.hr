package com.astontech.hr.domain.VO;

public class VehicleModelVO {

    private String newVehicleMakeType;
    private String newVehicleModels;
    private String[] newVehicleModelArray;

    public VehicleModelVO() {
    }

    public void SplitVehicleModelsToArray(){
        this.setNewVehicleModelArray(this.newVehicleModels.split("\\r?\\n"));
    }


    public String getNewVehicleMakeType() {
        return newVehicleMakeType;
    }

    public void setNewVehicleMakeType(String newVehicleMakeType) {
        this.newVehicleMakeType = newVehicleMakeType;
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
