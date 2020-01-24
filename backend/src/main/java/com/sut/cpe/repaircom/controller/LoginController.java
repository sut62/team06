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


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController{
String username;
    @Autowired
    ProfileloginRepository profileloginRepository;

    LoginController(ProfileloginRepository profileloginRepository) {
        this.profileloginRepository = profileloginRepository;
    }
    
    @PostMapping("/validate/{password}/{username}")
    public HashMap<String, String> login( 
    @PathVariable String username,
    @PathVariable String password){
    HashMap<String,String> map = new HashMap<>();
    Profilelogin profilelogin = profileloginRepository.findByUsername(username);
    try{
        if(profilelogin.getUsername().equals("customer")){
            if(profilelogin.getPassword().equals(password)){
            map.put("customer","true");
            }
            else{
                map.put("customer","false");
            }
        }
        else if(profilelogin.getUsername().equals("fix")) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("fix","true");
                }
            else {
                map.put("fix","false");
            }
        }
        else if(profilelogin.getUsername().equals("receipt")) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("receipt","true");
                }
            else {
                map.put("receipt","false");
            }
        }
        else if(profilelogin.getUsername().equals("addproduct")) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("addproduct","true");
            }
            else {
                map.put("addproduct","false");
            }
        }

        else if(profilelogin.getUsername().equals("admin")) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("admin","true");
                }
            else {
                map.put("admin","false");
            }
        }
        else if(profilelogin.getUsername().equals("contact")) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("contact","true");
            }
            else {
                map.put("contact","false");
            }
        }
        else if(profilelogin.getUsername().equals(username)) {
            if(profilelogin.getPassword().equals(password)) {
                map.put("contact","true");
                }
            else {
                map.put("contact","false");
            }
        }
        else
            map.put("massage","false");
        
    }
    catch (Exception e){
        map.put("message", "test");
    }
        return map;
    }

    @GetMapping("/getuser")
    public Profilelogin getUser(){
        return profileloginRepository.findByUsername(username);
    }
}