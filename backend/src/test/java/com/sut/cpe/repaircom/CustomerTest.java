package com.sut.cpe.repaircom;

import net.bytebuddy.utility.RandomString;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.CreatedBy;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@DataJpaTest
public class CustomerTest {

    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SexRepository sexRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b5915040_customerSuccess(){
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("parin boorapa");
        customer.setIdentification("1319800218977");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        customer = customerRepository.saveAndFlush(customer);
        final Optional<Customer> found = customerRepository.findById(customer.getId());

        assertEquals("parin boorapa", found.get().getCusName());
        assertEquals("1319800218977", found.get().getIdentification());
        assertEquals(21, found.get().getAge());
        try {
            assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"), found.get().getBirth());
        }
        catch (Exception e) {
            System.out.println("weong format");
        }
        assertEquals("63 หมู่่ 14 บ้านหัวเห็ด", found.get().getAddress());
        assertEquals("ห้วยหิน", found.get().getSubDistrict());
        assertEquals("หนองหงส์", found.get().getDistrict());
        assertEquals("0939898774", found.get().getTel());
        assertEquals("Vavo@hotmail.com", found.get().getEmail());
        assertEquals(1L, found.get().getCustomerSex().getId());
        assertEquals(1L, found.get().getCustomerProvince().getId());
        assertEquals(1L, found.get().getCreatedBy().getId());
    }
    
    @Test
    void b5915040_identificationMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("parin boorapa");
        customer.setIdentification(null);
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");
      
        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("identification", v.getPropertyPath().toString());
    }
    @Test
    void b5915040_customerNameMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName(null);
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cusName", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_AgeMustNotNegativeNumber() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("papa baba");
        customer.setIdentification("1021544258744");
        customer.setAge(-12);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_birthMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        customer.setBirth(null);
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("birth", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_addressMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress(null);
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_subDistrictMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict(null);
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("subDistrict", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_districtMustNotBeNull() {

        Customer customer = new Customer();
        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("หัวหิน");
        customer.setDistrict(null);
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("district", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_telMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยราช");
        customer.setDistrict("หนองหงส์");
        customer.setTel(null);
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_emailMustNotBeNull() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("AA BB");
        customer.setIdentification("1021544258744");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยราช");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0212125489");
        customer.setEmail(null);

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_identificationPattern() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("parin boorapa");
        customer.setIdentification("131254412548");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("identification", v.getPropertyPath().toString());
    }

    @Test
    void b5915040_telsize() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("parin boorapa");
        customer.setIdentification("5485122545899");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("15");
        customer.setEmail("Vavo@hotmail.com");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        assertEquals(1, result.size());

        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("size must be between 10 and 10",v.getMessage());
        assertEquals("tel",v.getPropertyPath().toString());
    }

    @Test
    void b5915040_emailValidation() {
        Customer customer = new Customer();

        customer.setCustomerSex(sexRepository.findById(1L));
        customer.setCustomerProvince(provinceRepository.findById(1L));
        customer.setCreatedBy(employeeRepository.findById(1L));
        customer.setCusName("parin boorapa");
        customer.setIdentification("1514211254877");
        customer.setAge(21);
        try {
            customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
        }
        catch (Exception e) {
            System.out.println("wrong format");
        }
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");
        customer.setEmail("gg");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

}



