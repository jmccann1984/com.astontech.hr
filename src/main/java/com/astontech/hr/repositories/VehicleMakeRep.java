package com.astontech.hr.repositories;

import com.astontech.hr.domain.VehicleMake;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleMakeRep extends CrudRepository<VehicleMake, Integer> {

    VehicleMake findByVehicleMakeName(String vehicleMakeName);
    List<VehicleMake> findAllByVehicleMakeName (String vehicleMakeName);
    List<VehicleMake> findAllByVehicleMakeNameIgnoreCase(String vehicleMakeName);
}
