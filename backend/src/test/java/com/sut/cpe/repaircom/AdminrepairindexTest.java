package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Adminrepairindex;
import com.sut.cpe.repaircom.repository.AdminrepairindexRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

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





@DataJpaTest
public class AdminrepairindexTest {
    
    private Validator validator;

    @Autowired
    private AdminrepairindexRepository adminrepairindexRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b5910168_testAdminrepairSuccess(){
        Adminrepairindex adminrepairindex = new Adminrepairindex();
        Date date = new Date();
        adminrepairindex.setBreakdown("เปิดไม่ติด");
        adminrepairindex.setTotalrepairprice(12345.00);
        adminrepairindex.setRepairwork("R1234567");
        adminrepairindex.setRepairDate(date);
        

        adminrepairindex = adminrepairindexRepository.saveAndFlush(adminrepairindex);
        Optional<Adminrepairindex> found = adminrepairindexRepository.findById(adminrepairindex.getId());

        assertEquals("เปิดไม่ติด", found.get().getBreakdown());
        assertEquals(12345, 12345,found.get().getTotalrepairprice());
        assertEquals("R1234567", found.get().getRepairwork());
        assertEquals(date,found.get().getRepairDate());

    }

    @Test 
    void b5910168_testAdminrepairBreakdownMustnotBenull(){
        Adminrepairindex adminrepairindex = new Adminrepairindex();
        Date date = new Date();
        adminrepairindex.setBreakdown(null);
        adminrepairindex.setTotalrepairprice(123456.00);
        adminrepairindex.setRepairwork("R1234567");
        adminrepairindex.setRepairDate(date);
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
        
      }

     @Test
     void b5910168_testAdminrepairTestPattern(){
         Adminrepairindex adminrepairindex = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex.setBreakdown("@#123$%^!@#$_");
         adminrepairindex.setTotalrepairprice(123456.00);
         adminrepairindex.setRepairwork("R1234567");
         adminrepairindex.setRepairDate(date);
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must match \"[ก-์|A-z|\\s].+\"",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
     } 

     @Test
     void b5910168_testAdminrepairTestUniqueBreakdown() {
         
         Adminrepairindex adminrepairindex1 = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex1.setBreakdown("เปิดไม่ติด");
         adminrepairindex1.setTotalrepairprice(123456.00);
         adminrepairindex1.setRepairwork("R1234567");
         adminrepairindex1.setRepairDate(date);
         adminrepairindexRepository.saveAndFlush(adminrepairindex1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

         Adminrepairindex adminrepairindex2 = new Adminrepairindex();
         Date date2 = new Date();
         adminrepairindex2.setBreakdown("เปิดไม่ติด");
         adminrepairindex2.setTotalrepairprice(123456.00);
         adminrepairindex2.setRepairwork("R1234567");
         adminrepairindex2.setRepairDate(date2);
         adminrepairindexRepository.saveAndFlush(adminrepairindex2);
         });
     }
    @Test
     void b5910168_testAdminrepairRepairworkPatterncheck(){
        Adminrepairindex admin = new Adminrepairindex();
        Date date = new Date();
        admin.setBreakdown("เปิดไม่ติด");
        admin.setTotalrepairprice(123456.00);
        admin.setRepairwork("B1234567");
        admin.setRepairDate(date);
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(admin);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must match \"[R]\\d{7}\"", v.getMessage());
        assertEquals("repairwork", v.getPropertyPath().toString());
     } 
    @Test
     void b5910168_testAdminrepairTotalpriceNonNegativeValue(){
        Adminrepairindex admin = new Adminrepairindex();
        Date date = new Date();
        admin.setBreakdown("เปิดไม่ติด");
        admin.setTotalrepairprice(-12.00);
        admin.setRepairwork("R1234567");
        admin.setRepairDate(date);
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(admin);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("totalrepairprice", v.getPropertyPath().toString());
     } 
     @Test
     void b5910168_testAdminrepairTestRepairworkmustbeunique() {
         
         Adminrepairindex adminrepairindex1 = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex1.setBreakdown("เปิดไม่ติด");
         adminrepairindex1.setTotalrepairprice(123456.00);
         adminrepairindex1.setRepairwork("R1234567");
         adminrepairindex1.setRepairDate(date);
         adminrepairindexRepository.saveAndFlush(adminrepairindex1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

         Adminrepairindex adminrepairindex2 = new Adminrepairindex();
         Date date2 = new Date();
         adminrepairindex2.setBreakdown("ไฟเข้าจอไม่ติด");
         adminrepairindex2.setTotalrepairprice(123456.00);
         adminrepairindex2.setRepairwork("R1234567");
         adminrepairindex2.setRepairDate(date2);
         adminrepairindexRepository.saveAndFlush(adminrepairindex2);
         });
     }
}


















