package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.CommunicationRepository;
import com.sut.cpe.repaircom.entity.Communication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CommunicationController {

    @Autowired
    private final CommunicationRepository communicationRepository;

    public  CommunicationController(CommunicationRepository  communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    @GetMapping("/communication")
    public Collection<Communication> Communications() {
        return communicationRepository.findAll().stream().collect(Collectors.toList());
    }
}