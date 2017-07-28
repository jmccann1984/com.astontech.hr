package com.astontech.hr.repositories;

import com.astontech.hr.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleModelRep extends CrudRepository<VehicleModel, Integer> {

    VehicleModel findByVehicleModelName(String elementTypeName);
    List<VehicleModel> findAllByVehicleModelName (String vehicleModelName);
    List<VehicleModel> findAllByVehicleModelNameIgnoreCase(String vehicleModelName);
}
