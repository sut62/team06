package com.sut.cpe.repaircom.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.cpe.repaircom.entity.Sex;
import com.sut.cpe.repaircom.repository.SexRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SexController {

    @Autowired
    private final SexRepository sexRepository;

    public SexController(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    @GetMapping("/sex")
    public Collection<Sex> Sexs() {
        return sexRepository.findAll().stream().collect(Collectors.toList());
    }

}