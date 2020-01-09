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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.repository.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReceiptController {
    @Autowired
    private final ReceiptRepository receiptRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AddproductRepository addproductRepository;
   
   
    
    
        ReceiptController(ReceiptRepository receiptRepository){
            this.receiptRepository = receiptRepository;
        }


        @GetMapping("/receipt")
        public Collection<Receipt> Receipts(){
            return receiptRepository.findAll().stream().collect(Collectors.toList());
        }

        @PostMapping("/receipt/{identification}/{type_id}/{pro_id}/{proR2}/{proR3}/{proR4}/{proR5}/{employee_id}/{branch_id}/{receiptPrice}")
            public Receipt newReceipt(Receipt newReceipt,
            @PathVariable String identification,
            @PathVariable long type_id,
            @PathVariable long pro_id,
            @PathVariable String proR2,
            @PathVariable String proR3,
            @PathVariable String proR4,
            @PathVariable String proR5,
            @PathVariable long employee_id,
            @PathVariable long branch_id,
            @PathVariable String receiptPrice){
            
            Customer customer = customerRepository.findByIdentification(identification);
            Type type = typeRepository.findById(type_id);
            Addproduct addproduct = addproductRepository.findById(pro_id);
            Employee employee = employeeRepository.findById(employee_id);
            Branch branch = branchRepository.findById(branch_id);
            
           

            newReceipt.setCustomer(customer);
            newReceipt.setType(type);
            newReceipt.setAddproduct(addproduct);
            newReceipt.setEmployee(employee);
            newReceipt.setBranch(branch);
            newReceipt.setReceiptPrice(receiptPrice);
            newReceipt.setProR2(proR2);
            newReceipt.setProR3(proR3);
            newReceipt.setProR4(proR4);
            newReceipt.setProR5(proR5);
            newReceipt.setReceiptDate(new Date());
           

                return receiptRepository.save(newReceipt);
            }
}
