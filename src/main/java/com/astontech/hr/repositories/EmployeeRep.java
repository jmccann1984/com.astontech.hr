package com.astontech.hr.repositories;

import com.astontech.hr.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRep extends CrudRepository<Employee, Integer>{
}
