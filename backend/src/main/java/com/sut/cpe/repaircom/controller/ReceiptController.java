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
    private EmployeeRepository employeeRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AdminrepairindexRepository adminrepairindexRepository;
   
   
    
    
        ReceiptController(ReceiptRepository receiptRepository){
            this.receiptRepository = receiptRepository;
        }


        @GetMapping("/receipt")
        public Collection<Receipt> Receipts(){
            return receiptRepository.findAll().stream().collect(Collectors.toList());
        }
        @GetMapping("/receipt/{receiptNum}")
        public Receipt Receipts(@PathVariable String receiptNum) {
        Receipt receipt = receiptRepository.findByReceiptNum(receiptNum);
        return receipt;
        }

        @PostMapping("/receipt/{receiptNum}/{repairwork}/{receiptCusident}/{receiptCusname}/{receiptCustel}/{type_id}/{employee_id}/{branch_id}")
            public Receipt newReceipt(Receipt newReceipt,
            @PathVariable long type_id,
            @PathVariable long employee_id,
            @PathVariable long branch_id,
            @PathVariable String repairwork,
            @PathVariable String receiptCusident,
            @PathVariable String receiptCusname,
            @PathVariable String receiptCustel,
            @PathVariable String receiptNum){
            
            Type type = typeRepository.findById(type_id);
            Employee employee = employeeRepository.findById(employee_id);
            Branch branch = branchRepository.findById(branch_id);
            Adminrepairindex adminrepairindex = adminrepairindexRepository.findByRepairwork(repairwork);
            
            newReceipt.setAdminrepairindex(adminrepairindex);
            newReceipt.setReceiptNum(receiptNum);
            newReceipt.setReceiptCustel(receiptCustel);
            newReceipt.setReceiptCusname(receiptCusname);
            newReceipt.setReceiptCusident(receiptCusident);
            newReceipt.setType(type);
            newReceipt.setEmployee(employee);
            newReceipt.setBranch(branch);
            newReceipt.setReceiptDate(new Date());
           

                return receiptRepository.save(newReceipt);
            }
}
