package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.StatusrepairRepository;
import com.sut.cpe.repaircom.entity.Statusrepair;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StatusrepairController{

    @Autowired
    private final StatusrepairRepository statusrepairRepository;

    public  StatusrepairController(StatusrepairRepository  statusrepairRepository) {
        this.statusrepairRepository = statusrepairRepository;
    }

    @GetMapping("/statusrepair")
    public Collection<Statusrepair> statusrepairs() {
        return statusrepairRepository.findAll().stream().collect(Collectors.toList());
    }
}