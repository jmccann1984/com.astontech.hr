package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.repositories.VehicleMakeRep;
import com.astontech.hr.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    @Autowired
    private VehicleMakeRep vehicleMakeRep;

    @Override
    public Iterable<VehicleMake> listAllVehicleMake() {
        return vehicleMakeRep.findAll();
    }

    @Override
    public VehicleMake getVehicleMakeById(Integer id) {
        return vehicleMakeRep.findOne(id);
    }

    @Override
    public VehicleMake saveVehicleMake(VehicleMake vehicleMake) {
        return vehicleMakeRep.save(vehicleMake);
    }

    @Override
    public Iterable<VehicleMake> saveVehicleMakeList(Iterable<VehicleMake> vehicleMakeIterable) {
        return vehicleMakeRep.save(vehicleMakeIterable);
    }

    @Override
    public void deleteVehicleMake(Integer id) {
        vehicleMakeRep.delete(id);
    }

    @Override
    public VehicleMake findByVehicleMakeName(String vehicleMakeName) {
        return vehicleMakeRep.findByVehicleMakeName(vehicleMakeName);
    }

    @Override
    public List<VehicleMake> findAllByVehicleMakeName(String vehicleMakeName) {
        return vehicleMakeRep.findAllByVehicleMakeName(vehicleMakeName);
    }

    @Override
    public List<VehicleMake> findAllByVehicleMakeNameIgnoreCase(String vehicleMakeName) {
        return vehicleMakeRep.findAllByVehicleMakeNameIgnoreCase(vehicleMakeName);
    }
}
