package com.sut.cpe.repaircom.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FixController {
    @Autowired
    private final FixRepository fixRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FixtypeRepository fixtypeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private MachinecolorRepository machinecolorRepository;

  
    FixController(FixRepository fixRepository) {
        this.fixRepository = fixRepository;
    }

    @GetMapping("/fix")
    public Collection<Fix> fixs() {
        return fixRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/fix/{employee_id}/{customer_id}/{fixtype_id}/{machinecolor_id}/{brand_id}/{queue}/{fixname}")
    public Fix newFix(Fix newFix,
    @PathVariable long employee_id,
    @PathVariable long customer_id,
    @PathVariable long fixtype_id,
    @PathVariable long machinecolor_id,
    @PathVariable long brand_id,
    @PathVariable String queue,
    @PathVariable String fixname)
    
    
    {
    //Fix newFix = new Fix();
    Employee employee = employeeRepository.findById(employee_id);
    Customer customer = customerRepository.findById(customer_id);
    Fixtype fixtype = fixtypeRepository.findById(fixtype_id);
    Machinecolor machinecolor = machinecolorRepository.findById(machinecolor_id);
    Brand brand = brandRepository.findById(brand_id);
    
    
    newFix.setEmployee(employee);
    newFix.setCustomer(customer);
    newFix.setFixtype(fixtype);
    newFix.setMachinecolor(machinecolor);
    newFix.setBrand(brand);
    newFix.setQueue(queue);
    newFix.setFixname(fixname);
    
    
    
    return fixRepository.save(newFix); 
    
    }
}