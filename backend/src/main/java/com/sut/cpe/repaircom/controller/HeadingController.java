package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.HeadingRepository;
import com.sut.cpe.repaircom.entity.Heading;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class HeadingController {

    @Autowired
    private final HeadingRepository headingRepository;

    public  HeadingController(HeadingRepository  headingRepository) {
        this.headingRepository = headingRepository;
    }

    @GetMapping("/heading")
    public Collection<Heading> Headings() {
        return headingRepository.findAll().stream().collect(Collectors.toList());
    }
}