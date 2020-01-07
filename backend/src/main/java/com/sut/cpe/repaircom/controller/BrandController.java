package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.BrandRepository;
import com.okta.springbootvue.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BrandController {
    @Autowired
    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brand")
    public Collection<Brand> Brands() {
        return brandRepository.findAll().stream().collect(Collectors.toList());
    }

}