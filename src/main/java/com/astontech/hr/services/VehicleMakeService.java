package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleMake;

import java.util.List;

public interface VehicleMakeService {

    Iterable<VehicleMake> listAllVehicleMake();
    VehicleMake getVehicleMakeById(Integer id);
    VehicleMake saveVehicleMake(VehicleMake vehicleMake);
    Iterable<VehicleMake> saveVehicleMakeList(Iterable<VehicleMake> vehicleMakeIterable);
    void deleteVehicleMake(Integer id);

    VehicleMake findByVehicleMakeName(String vehicleMakeName);
    List<VehicleMake> findAllByVehicleMakeName (String vehicleMakeName);
    List<VehicleMake> findAllByVehicleMakeNameIgnoreCase(String vehicleMakeName);
}
