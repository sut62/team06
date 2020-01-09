package com.sut.cpe.repaircom.controller;

import com.sut.cpe.repaircom.repository.ProfileloginRepository;
import com.sut.cpe.repaircom.entity.Profilelogin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



@EnableAutoConfiguration
@RestController
@CrossOrigin("http://localhost:8080")
public class RegisterController {
    @Autowired
    private ProfileloginRepository profileloginRepository;

    @PostMapping("/adduser/{password}/{username}")
    public Profilelogin newUser(
        Profilelogin newprofilelogin,
        @PathVariable String username,
        @PathVariable String password){
       
       
           
            newprofilelogin.setUsername(username);
            newprofilelogin.setPassword(password);
       
        return  profileloginRepository.save(newprofilelogin);
    }

    
}