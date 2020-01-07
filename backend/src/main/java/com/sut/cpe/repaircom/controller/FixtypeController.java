package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.FixtypeRepository;
import com.okta.springbootvue.entity.Fixtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FixtypeController {
    @Autowired
    private final FixtypeRepository fixtypeRepository;

    public FixtypeController(FixtypeRepository fixtypeRepository) {
        this.fixtypeRepository = fixtypeRepository;
    }

    @GetMapping("/fixtype")
    public Collection<Fixtype> Fixtypes() {
        return fixtypeRepository.findAll().stream().collect(Collectors.toList());
    }

}