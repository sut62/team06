package com.sut.cpe.repaircom.controller;

import com.sut.cpe.repaircom.repository.PartfixRepository;
import com.sut.cpe.repaircom.entity.Partfix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PartfixController {
    @Autowired
    private final PartfixRepository partfixRepository;

    public PartfixController(PartfixRepository partfixRepository) {
        this.partfixRepository = partfixRepository;
    }

    @GetMapping("/partfix")
    public Collection<Partfix> Partfixs() {
        return partfixRepository.findAll().stream().collect(Collectors.toList());
    }
    
}