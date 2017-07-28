package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.repositories.VehicleModelRep;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelServiceImpl implements VehicleModelService{

    @Autowired
    VehicleModelRep vehicleModelRep;

    @Override
    public Iterable<VehicleModel> listAllVehicleModel() {
        return vehicleModelRep.findAll();
    }

    @Override
    public VehicleModel getVehicleModelById(Integer id) {
        return vehicleModelRep.findOne(id);
    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRep.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable) {
        return vehicleModelRep.save(vehicleModelIterable);
    }

    @Override
    public void deleteVehicleModel(Integer id) {
        vehicleModelRep.delete(id);
    }

    @Override
    public VehicleModel findByVehicleModelName(String vehicleModelName) {
        return vehicleModelRep.findByVehicleModelName(vehicleModelName);
    }

    @Override
    public List<VehicleModel> findAllByVehicleModelName(String vehicleModelName) {
        return vehicleModelRep.findAllByVehicleModelName(vehicleModelName);
    }

    @Override
    public List<VehicleModel> findAllByVehicleModelNameIgnoreCase(String vehicleModelName) {
        return vehicleModelRep.findAllByVehicleModelNameIgnoreCase(vehicleModelName);
    }
}
