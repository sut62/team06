package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.PartpiecesnumberforrepairRepository;
import com.sut.cpe.repaircom.entity.Partpiecesnumberforrepair;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PartpiecesnumberforrepairController{

    @Autowired
    private final PartpiecesnumberforrepairRepository partpiecesnumberforrepairRepository;

    public  PartpiecesnumberforrepairController(PartpiecesnumberforrepairRepository  partpiecesnumberforrepairRepository) {
        this.partpiecesnumberforrepairRepository = partpiecesnumberforrepairRepository;
    }

    @GetMapping("/partpiece")
    public Collection<Partpiecesnumberforrepair> Partpiecesnumberforrepairs() {
        return partpiecesnumberforrepairRepository.findAll().stream().collect(Collectors.toList());
    }
}