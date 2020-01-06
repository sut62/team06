package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.TypemachrepairRepository;
import com.sut.cpe.repaircom.entity.Typemachrepair;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypemachrepairController{

    @Autowired
    private final TypemachrepairRepository typemachrepairRepository;

    public  TypemachrepairController(TypemachrepairRepository  typemachrepairRepository) {
        this.typemachrepairRepository = typemachrepairRepository;
    }

    @GetMapping("/typemachrepair")
    public Collection<Typemachrepair> typemachrepairs() {
        return typemachrepairRepository.findAll().stream().collect(Collectors.toList());
    }
}