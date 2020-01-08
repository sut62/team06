package com.sut.cpe.repaircom.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ContactController {
    @Autowired
    private final ContactRepository contactRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CommunicationRepository  communicationRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private HeadingRepository headingRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contact")
    public Collection<Contact> Contacts() {
        return contactRepository.findAll().stream().collect(Collectors.toList());
    }

               
  
   
    @PostMapping("/contact/{identificationnumber}/{communication_id}/{email}/{phone}/{branch_id}/{heading_id}/{detail}")
    public Contact newContact(Contact newContact,
        @PathVariable String identificationnumber,   
        @PathVariable long communication_id,
        @PathVariable String email,
        @PathVariable String phone,
        @PathVariable long branch_id, 
        @PathVariable long heading_id,
        @PathVariable String detail)
    {
        
        Customer createdBy = customerRepository.findByIdentificationNumber(identificationnumber);
        Communication communication = communicationRepository.findById(communication_id);
        Branch branch = branchRepository.findById(branch_id);
        Heading heading = headingRepository.findById(heading_id);

        newContact.setCreatedBy(createdBy);
        newContact.setCommunication(communication);
        newContact.setEmail(email);
        newContact.setPhone(phone);
        newContact.setBranch(branch);
        newContact.setHeading(heading);
        newContact.setDetail(detail);
        
        return contactRepository.save(newContact);
    }
}