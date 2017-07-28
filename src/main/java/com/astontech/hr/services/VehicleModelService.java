package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleModel;

import java.util.List;

public interface VehicleModelService {

    Iterable<VehicleModel> listAllVehicleModel();
    VehicleModel getVehicleModelById(Integer id);
    VehicleModel saveVehicleModel(VehicleModel vehicleModel);
    Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable);
    void deleteVehicleModel(Integer id);

    VehicleModel findByVehicleModelName(String vehicleModelName);
    List<VehicleModel> findAllByVehicleModelName (String vehicleModelName);
    List<VehicleModel> findAllByVehicleModelNameIgnoreCase(String vehicleModelName);
    
}
