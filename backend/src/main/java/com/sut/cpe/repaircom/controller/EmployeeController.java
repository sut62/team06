package com.sut.cpe.repaircom.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.cpe.repaircom.entity.Employee;
import com.sut.cpe.repaircom.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

}