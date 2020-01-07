package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.BranchRepository;
import com.okta.springbootvue.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BranchController {

    @Autowired
    private final BranchRepository branchRepository;

    public BranchController(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @GetMapping("/branch")
    public Collection<Branch> Branchs() {
        return branchRepository.findAll().stream().collect(Collectors.toList());
    }

}