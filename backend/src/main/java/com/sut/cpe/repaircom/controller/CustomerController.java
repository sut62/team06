package com.sut.cpe.repaircom.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;
import java.net.URLDecoder;

import com.sut.cpe.repaircom.entity.Customer;
import com.sut.cpe.repaircom.entity.Employee;
import com.sut.cpe.repaircom.entity.Sex;
import com.sut.cpe.repaircom.entity.Province;
import com.sut.cpe.repaircom.repository.CustomerRepository;
import com.sut.cpe.repaircom.repository.EmployeeRepository;
import com.sut.cpe.repaircom.repository.SexRepository;
import com.sut.cpe.repaircom.repository.ProvinceRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CustomerController {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SexRepository sexRepository;
  
    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //ดึงข้อมูล
    @GetMapping("/customer")
    public Collection<Customer> Customers() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    //ลบข้อมูล
    @DeleteMapping("/customer/{customer_id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customer_id") long customer_id) {
		System.out.println("Delete Customer with ID = " + customer_id + "...");

		customerRepository.deleteById(customer_id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}
    //สร้างข้อมูล
    @PostMapping("/customer/{sex_id}/{subDistrict}/{district}/{province_id}/{employee_id}/{cusName}/{identification}/{age}/{address}/{tel}/{birth}")
    public Customer newCustomer(
    @PathVariable long sex_id,
    @PathVariable String subDistrict,
    @PathVariable String district,
    @PathVariable long province_id,
    @PathVariable long employee_id,
    @PathVariable String cusName,
    @PathVariable String identification,
    @PathVariable int age,
    @PathVariable String address,
    @PathVariable String tel,
    @PathVariable String birth){

    Customer newCustomer = new Customer();
    Sex customerSex = sexRepository.findById(sex_id);
    Province customerProvince = provinceRepository.findById(province_id);
    Employee createdBy = employeeRepository.findById(employee_id);

    newCustomer.setCustomerSex(customerSex);
    newCustomer.setCustomerProvince(customerProvince);
    newCustomer.setCreatedBy(createdBy);

    newCustomer.setCusName(cusName);
    newCustomer.setSubDistrict(subDistrict);
    newCustomer.setDistrict(district);
    newCustomer.setIdentification(identification);
    newCustomer.setAge(age);
    newCustomer.setAddress(address);
    newCustomer.setTel(tel);
    
    //convert String to Date
    try {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse(birth);
        newCustomer.setBirth(date);
    }
    catch (Exception e) {
        System.out.println(e);
    }
    
    return customerRepository.save(newCustomer); 
    
    }
}