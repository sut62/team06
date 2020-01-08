package com.sut.cpe.repaircom.controller;
import com.sut.cpe.repaircom.repository.PartRepository;
import com.sut.cpe.repaircom.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PartController {
    @Autowired
    private final PartRepository partRepository;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping("/part")
    public Collection<Part> Parts() {
        return partRepository.findAll().stream().collect(Collectors.toList());
    }

}