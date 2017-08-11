package com.astontech.hr.rest;

import com.astontech.hr.domain.Employee;
import com.astontech.hr.services.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

    Logger log = Logger.getLogger(EmployeeRest.class);

    @Autowired
    private EmployeeService employeeService;

    // GET ALL
    @RequestMapping(value="/", method = RequestMethod.GET)
    public Iterable<Employee> getAll() {
        return employeeService.listAllEmployees();
    }

    // GET BY ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Employee getByeId(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    // SAVE
    @RequestMapping(value="/", method = RequestMethod.POST)
    public Employee save(@ModelAttribute("employee") Employee employee){
        return employeeService.saveEmployee(employee);
    }

    // DELETE
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try{
            employeeService.deleteEmployee(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }

        return result;
    }
}
