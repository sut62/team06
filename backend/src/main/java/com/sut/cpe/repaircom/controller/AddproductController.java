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
public class AddproductController {
    @Autowired
    private final AddproductRepository addproductRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private BranchRepository branchRepository;
  
    AddproductController(AddproductRepository addproductRepository) {
        this.addproductRepository = addproductRepository;
    }

    @GetMapping("/addproduct")
    public Collection<Addproduct> addproducts() {
        return addproductRepository.findAll().stream().collect(Collectors.toList());
    }
    // @GetMapping("/addproduct/{id}")
    // public Addproduct addproduct(@PathVariable long id) {
    //     Addproduct addproduct = addproductRepository.findById(id);
    //     return addproduct;
    // }
    @GetMapping("/addproduct/{proid}")
    public Addproduct Addproducts(@PathVariable String proid) {
        Addproduct addproduct = addproductRepository.findByProid(proid);
        return addproduct;
    }

    @PostMapping("/addproduct/{proid}/{productname}/{description}/{type_id}/{brand_id}/{part_id}/{branch_id}/{price}")
    public Addproduct newAddproduct(Addproduct newAddproduct,
    @PathVariable String proid,
    @PathVariable String productname,
    @PathVariable String description,
    @PathVariable long type_id,
    @PathVariable long brand_id,
    @PathVariable long part_id,
    @PathVariable long branch_id,
    @PathVariable int price) 
    
    {
         
    Type type = typeRepository.findById(type_id);
    Brand brand = brandRepository.findById(brand_id);
    Part part = partRepository.findById(part_id);
    Branch  branch = branchRepository.findById(branch_id);
    newAddproduct.setProid(proid);
    newAddproduct.setProductname(productname);
    newAddproduct.setDescription(description);
    newAddproduct.setType(type);
    newAddproduct.setBrand(brand);
    newAddproduct.setPart(part);
    newAddproduct.setBranch(branch);
    newAddproduct.setPrice(price);
    
    return addproductRepository.save(newAddproduct); 
    
    }
}