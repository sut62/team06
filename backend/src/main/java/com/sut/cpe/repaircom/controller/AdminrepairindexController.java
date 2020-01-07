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
public class AdminrepairindexController {
    @Autowired
    private final AdminrepairindexRepository adminrepairindexRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StatusrepairRepository statusrepairRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AddproductRepository addproductRepository;
    @Autowired
    private PartpiecesnumberforrepairRepository partpiecesnumberforrepairRepository;
    
    
        AdminrepairindexController(AdminrepairindexRepository adminrepairindexRepository){
            this.adminrepairindexRepository = adminrepairindexRepository;
        }


        @GetMapping("/Adminrepairindex")
        public Collection<Adminrepairindex> Adminrepairindexs(){
            return adminrepairindexRepository.findAll().stream().collect(Collectors.toList());
        }
        @PostMapping("/Adminrepairindex/{breakdown}/{partn2}/{partn3}/{partn4}/{partn5}/{addproduct_id}/{branch_id}/{brand_id}/{employee_id}/{identification}/{statusrepair_id}/{type_id}/{partpiecesnumberforrepair_id}")
            public Adminrepairindex newAdminrepairindex(Adminrepairindex newAdminrepairindex,
            @PathVariable String breakdown,
            @PathVariable String partn2,
            @PathVariable String partn3,
            @PathVariable String partn4,
            @PathVariable String partn5,
            @PathVariable long addproduct_id,
            @PathVariable long branch_id,
            @PathVariable long brand_id,
            @PathVariable long employee_id,
            @PathVariable String identification,
            @PathVariable long statusrepair_id,
            @PathVariable long type_id,
            @PathVariable long partpiecesnumberforrepair_id){
            
            Addproduct addproduct = addproductRepository.findById(addproduct_id);
            Branch branch = branchRepository.findById(branch_id);
            Brand brand = brandRepository.findById(brand_id);
            Employee createdBy = employeeRepository.findById(employee_id);
            Customer customer = customerRepository.findByIdentification(identification);
            Statusrepair statusrepair = statusrepairRepository.findById(statusrepair_id);
            Type type = typeRepository.findById(type_id);
            Partpiecesnumberforrepair partpiecesnumberforrepair = partpiecesnumberforrepairRepository.findById(partpiecesnumberforrepair_id);

            newAdminrepairindex.setBreakdown(breakdown);
            newAdminrepairindex.setPartn2(partn2);
            newAdminrepairindex.setPartn3(partn3);
            newAdminrepairindex.setPartn4(partn4);
            newAdminrepairindex.setPartn5(partn5);
            newAdminrepairindex.setAddproduct(addproduct);
            newAdminrepairindex.setRepairDate(new Date());
            newAdminrepairindex.setBranch(branch);
            newAdminrepairindex.setBrand(brand);
            newAdminrepairindex.setCreatedBy(createdBy);
            newAdminrepairindex.setCustomer(customer);
            newAdminrepairindex.setStatusrepair(statusrepair);
            newAdminrepairindex.setType(type);
            newAdminrepairindex.setPartpiecesnumberforrepair(partpiecesnumberforrepair);


                return adminrepairindexRepository.save(newAdminrepairindex);
            }
}
