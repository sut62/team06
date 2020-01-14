package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Customer;
import com.sut.cpe.repaircom.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class CustomerTest {

    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b5915040_testCustomerSuccess(){
        Customer customer = new Customer();

        customer.setCusName("parin boorapa");
        customer.setIdentification("1319800218977");
        customer.setAge(21);
        customer.setBirth(null);
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");

        customer = customerRepository.saveAndFlush(customer);
        final Optional<Customer> found = customerRepository.findById(customer.getId());

        assertEquals("parin boorapa", found.get().getCusName());
        assertEquals("1319800218977", found.get().getIdentification());
        assertEquals(21, found.get().getAge());
        assertEquals(null, found.get().getBirth());
        assertEquals("63 หมู่่ 14 บ้านหัวเห็ด", found.get().getAddress());
        assertEquals("ห้วยหิน", found.get().getSubDistrict());
        assertEquals("หนองหงส์", found.get().getDistrict());
        assertEquals("0939898774", found.get().getTel());
    }
    /*@Test
    void b5915040_testCustomerNull(){
        Customer customer = new Customer();

        customer.setCusName(null);
        customer.setIdentification("1319800218977");
        customer.setAge(21);
        customer.setBirth(null);
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");

        customer = customerRepository.saveAndFlush(customer);
        final Optional<Customer> found = customerRepository.findById(customer.getId());

        assertEquals("Must Not Be Null", v.getMessage());
        assertEquals("1319800218977", found.get().getIdentification());
        assertEquals(21, found.get().getAge());
        assertEquals(null, found.get().getBirth());
        assertEquals("63 หมู่่ 14 บ้านหัวเห็ด", found.get().getAddress());
        assertEquals("ห้วยหิน", found.get().getSubDistrict());
        assertEquals("หนองหงส์", found.get().getDistrict());
        assertEquals("0939898774", found.get().getTel());
    }
    @Test
    void b5915040_testCustomerSize(){
        Customer customer = new Customer();

        customer.setCusName(null);
        customer.setIdentification("1319800218977");
        customer.setAge(21);
        customer.setBirth(null);
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");

        customer = customerRepository.saveAndFlush(customer);
        final Optional<Customer> found = customerRepository.findById(customer.getId());

        assertEquals(null, found.get().getCusName());
        assertEquals("1319800218977", found.get().getIdentification());
        assertEquals(21, found.get().getAge());
        assertEquals(null, found.get().getBirth());
        assertEquals("63 หมู่่ 14 บ้านหัวเห็ด", found.get().getAddress());
        assertEquals("ห้วยหิน", found.get().getSubDistrict());
        assertEquals("หนองหงส์", found.get().getDistrict());
        assertEquals("0939898774", found.get().getTel());
    }
    @Test
    void b5915040_testCustomerPattern(){
        Customer customer = new Customer();

        customer.setCusName(null);
        customer.setIdentification("1319800218977");
        customer.setAge(21);
        customer.setBirth(null);
        customer.setAddress("63 หมู่่ 14 บ้านหัวเห็ด");
        customer.setSubDistrict("ห้วยหิน");
        customer.setDistrict("หนองหงส์");
        customer.setTel("0939898774");

        customer = customerRepository.saveAndFlush(customer);
        final Optional<Customer> found = customerRepository.findById(customer.getId());

        assertEquals(null, found.get().getCusName());
        assertEquals("1319800218977", found.get().getIdentification());
        assertEquals(21, found.get().getAge());
        assertEquals(null, found.get().getBirth());
        assertEquals("63 หมู่่ 14 บ้านหัวเห็ด", found.get().getAddress());
        assertEquals("ห้วยหิน", found.get().getSubDistrict());
        assertEquals("หนองหงส์", found.get().getDistrict());
        assertEquals("0939898774", found.get().getTel());
    }*/

}



