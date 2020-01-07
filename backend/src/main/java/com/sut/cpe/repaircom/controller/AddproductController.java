package com.okta.springbootvue.controller;

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

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

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

    @PostMapping("/addproduct/{productname}/{description}/{type_id}/{brand_id}/{part_id}/{branch_id}")
    public Addproduct newAddproduct(
    @PathVariable String productname,
    @PathVariable String description,
    @PathVariable long type_id,
    @PathVariable long brand_id,
    @PathVariable long part_id,
    @PathVariable long branch_id) 
    
    {
        Addproduct newAddproduct = new Addproduct();
    Type type = typeRepository.findById(type_id);
    Brand brand = brandRepository.findById(brand_id);
    Part part = partRepository.findById(part_id);
    Branch  branch = branchRepository.findById(branch_id);
    newAddproduct.setProductname(productname);
    newAddproduct.setDescription(description);
    newAddproduct.setType(type);
    newAddproduct.setBrand(brand);
    newAddproduct.setPart(part);
    newAddproduct.setBranch(branch);
    
    return addproductRepository.save(newAddproduct); 
    
    }
}