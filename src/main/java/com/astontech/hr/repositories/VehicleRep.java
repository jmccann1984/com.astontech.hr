package com.astontech.hr.repositories;

import com.astontech.hr.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRep extends CrudRepository<Vehicle, Integer> {
}
