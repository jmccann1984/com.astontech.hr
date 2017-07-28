package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.repositories.VehicleRep;
import com.astontech.hr.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRep vehicleRep;

    @Override
    public Iterable<Vehicle> listAllVehicle() {
        return vehicleRep.findAll();
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehicleRep.findOne(id);
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRep.save(vehicle);
    }

    @Override
    public Iterable<Vehicle> saveVehicleList(Iterable<Vehicle> vehicleIterable) {
        return vehicleRep.save(vehicleIterable);
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehicleRep.delete(id);
    }
}
