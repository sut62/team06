package com.sut.cpe.repaircom.controller;

import com.sut.cpe.repaircom.repository.MachinecolorRepository;
import com.sut.cpe.repaircom.entity.Machinecolor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MachinecolorController {
    @Autowired
    private final MachinecolorRepository machinecolorRepository;

    public MachinecolorController(MachinecolorRepository machinecolorRepository) {
        this.machinecolorRepository = machinecolorRepository;
    }

    @GetMapping("/machinecolor")
    public Collection<Machinecolor> Machinecolors() {
        return machinecolorRepository.findAll().stream().collect(Collectors.toList());
    }

}