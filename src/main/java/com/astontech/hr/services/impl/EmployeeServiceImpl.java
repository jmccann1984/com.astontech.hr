package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Employee;
import com.astontech.hr.repositories.EmployeeRep;
import com.astontech.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRep employeeRep;

    @Autowired
    public EmployeeServiceImpl(EmployeeRep employeeRep) { this.employeeRep = employeeRep;
    }


    @Override
    public Iterable<Employee> listAllEmployees() {
        return employeeRep.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRep.findOne(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRep.save(employee);
    }

    @Override
    public Iterable<Employee> saveEmployeeList(Iterable<Employee> employeeIterable) {
        return employeeRep.save(employeeIterable);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRep.delete(id);
    }
}
