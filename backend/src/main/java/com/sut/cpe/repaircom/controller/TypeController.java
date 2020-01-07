package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.TypeRepository;
import com.okta.springbootvue.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypeController {
    @Autowired
    private final TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping("/type")
    public Collection<Type> Types() {
        return typeRepository.findAll().stream().collect(Collectors.toList());
    }

}