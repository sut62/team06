package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.repository.*;
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
public class FixTest {

    private Validator validator;

    @Autowired
    private FixRepository fixRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private FixtypeRepository fixtypeRepository;

    @Autowired
    private MachinecolorRepository machinecolorRepository;
    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5907397_testFixSuccess(){
        Fix fix = new Fix();
      
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
       

        fix = fixRepository.saveAndFlush(fix);
        final Optional<Fix> found = fixRepository.findById(fix.getId());
      
        assertEquals("จอดับบ่อยๆ", found.get().getFixname());
        assertEquals("00012020", found.get().getQueue());
        assertEquals(1L, found.get().getCreatedBy().getId());
        assertEquals(1L, found.get().getCustomer().getId());
        assertEquals(1L, found.get().getBrand().getId());
        assertEquals(1L, found.get().getFixtype().getId());
        assertEquals(1L, found.get().getMachinecolor().getId());
        
        
    
    }  

    @Test
    void B5907397_testFixnameMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname(null);
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fixname", v.getPropertyPath().toString());
        
    }

    @Test
    void B5907397_testQueueMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue(null);
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("queue", v.getPropertyPath().toString());
    }

    @Test
    void B5907397_testFixnameSize() {
       Fix fix = new Fix();

       fix.setFixname("");
       fix.setQueue("00012020");
       fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
       Set<ConstraintViolation<Fix>> result = validator.validate(fix);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       ConstraintViolation<Fix> v = result.iterator().next();
       assertEquals("size must be between 1 and 200",v.getMessage());
       assertEquals("fixname",v.getPropertyPath().toString());
    }

    @Test
    void B5907397_testQueuePattern() {
        Fix fix = new Fix();

        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020yljh");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));

       
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must match \"\\d{8}\"", v.getMessage());
        assertEquals("queue", v.getPropertyPath().toString());
    }
   
    @Test
    void B5907397_testQueueMustBeUnique() {
        // สร้าง num_id object
        Fix f1 = new Fix();
        f1.setFixname("จอดับบ่อยๆ");
        f1.setQueue("00012020");
        f1.setCreatedBy(employeeRepository.findById(1L));
        f1.setCustomer(customerRepository.findById(1L));
        f1.setBrand(brandRepository.findById(1L));
        f1.setFixtype(fixtypeRepository.findById(1L));
        f1.setMachinecolor(machinecolorRepository.findById(1L));
    fixRepository.saveAndFlush(f1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง num_id object ตัวที่ 2
            Fix f2 = new Fix();
                f2.setFixname("จอดับบ่อยๆ");
                f2.setQueue("00012020");
                f2.setCreatedBy(employeeRepository.findById(1L));
                f2.setCustomer(customerRepository.findById(1L));
                f2.setBrand(brandRepository.findById(1L));
                f2.setFixtype(fixtypeRepository.findById(1L));
                f2.setMachinecolor(machinecolorRepository.findById(1L));
            fixRepository.saveAndFlush(f2);
        });
    }
    @Test
    void B5907397_testEmployeeMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(null);
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdBy", v.getPropertyPath().toString());
    }

    @Test
    void B5907397_testCustomerMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(null);
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("customer", v.getPropertyPath().toString());
    }

    @Test
    void B5907397_testBrandMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(null);
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("brand", v.getPropertyPath().toString());
    }
    @Test
    void B5907397_testFixtypeMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(null);
        fix.setMachinecolor(machinecolorRepository.findById(1L));
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fixtype", v.getPropertyPath().toString());
    }
@Test
    void B5907397_testMachinecolorMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
        fix.setCreatedBy(employeeRepository.findById(1L));
        fix.setCustomer(customerRepository.findById(1L));
        fix.setBrand(brandRepository.findById(1L));
        fix.setFixtype(fixtypeRepository.findById(1L));
        fix.setMachinecolor(null);
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("machinecolor", v.getPropertyPath().toString());
    }


}

