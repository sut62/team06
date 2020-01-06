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
    private FixRepository fixRepository;
    @Autowired
    private StatusrepairRepository statusrepairRepository;
    @Autowired
    private TypemachrepairRepository typemachrepairRepository;
    @Autowired
    private AddproductRepository addproductRepository;
    
    
        AdminrepairindexController(AdminrepairindexRepository adminrepairindexRepository){
            this.adminrepairindexRepository = adminrepairindexRepository;
        }


        @GetMapping("/Adminrepairindex")
        public Collection<Adminrepairindex> Adminrepairindexs(){
            return adminrepairindexRepository.findAll().stream().collect(Collectors.toList());
        }
        @PostMapping("/Adminrepairindex/{breakdown}/{addproduct_id}/{branch_id}/{brand_id}/{employee_id}/{identification}/{statusrepair_id}/{typemachrepair_id}")
            public Adminrepairindex newAdminrepairindex(Adminrepairindex newAdminrepairindex,
            @PathVariable String breakdown,
            @PathVariable long addproduct_id,
            @PathVariable long branch_id,
            @PathVariable long brand_id,
            @PathVariable long employee_id,
            @PathVariable String identification,
            @PathVariable long statusrepair_id,
            @PathVariable long typemachrepair_id){
            
            Addproduct addproduct = addproductRepository.findById(addproduct_id);
            Branch branch = branchRepository.findById(branch_id);
            Brand brand = brandRepository.findById(brand_id);
            Employee createdBy = employeeRepository.findById(employee_id);
            Fix fix = fixRepository.findByIdentification(identification);
            Statusrepair statusrepair = statusrepairRepository.findById(statusrepair_id);
            Typemachrepair typemachrepair = typemachrepairRepository.findById(typemachrepair_id);

            newAdminrepairindex.setBreakdown(breakdown);
            newAdminrepairindex.setAddproduct(addproduct);
            newAdminrepairindex.setRepairDate(new Date());
            newAdminrepairindex.setBranch(branch);
            newAdminrepairindex.setBrand(brand);
            newAdminrepairindex.setCreatedBy(createdBy);
            newAdminrepairindex.setFix(fix);
            newAdminrepairindex.setStatusrepair(statusrepair);
            newAdminrepairindex.setTypemachrepair(typemachrepair);


                return adminrepairindexRepository.save(newAdminrepairindex);
            }
}
