package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Adminrepairindex;
import com.sut.cpe.repaircom.repository.AdminrepairindexRepository;
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

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;





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

        adminrepairindex.setBreakdown("เปิดไม่ติด");
        adminrepairindex.setTotalrepairprice("123456");
        adminrepairindex.setRepairwork("R1234567");
    

        adminrepairindex = adminrepairindexRepository.saveAndFlush(adminrepairindex);
        final Optional<Adminrepairindex> found = adminrepairindexRepository.findById(adminrepairindex.getId());

        assertEquals("เปิดไม่ติด", found.get().getBreakdown());
        assertEquals("123456", found.get().getTotalrepairprice());
        assertEquals("R1234567", found.get().getRepairwork());

    }

    @Test 
    void b5910168_testAdminrepairBreakdownMustnotBenull(){
        Adminrepairindex adminrepairindex = new Adminrepairindex();

        adminrepairindex.setBreakdown(null);
        adminrepairindex.setTotalrepairprice("123456");
        adminrepairindex.setRepairwork("R1234567");

        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
      }

     @Test
     void b5910168_testAdminrepairTestPattern(){
         Adminrepairindex adminrepairindex = new Adminrepairindex();
         adminrepairindex.setBreakdown("@#123$%^!@#$_");
         adminrepairindex.setTotalrepairprice("123456");
         adminrepairindex.setRepairwork("R1234567");
    
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must match \"[ก-์|A-z|\\s].+\"",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
     } 

     @Test
     void b5910168_testAdminrepairTestUniqueBreakdown() {
         
         Adminrepairindex adminrepairindex1 = new Adminrepairindex();
         adminrepairindex1.setBreakdown("เปิดไม่ติด");
         adminrepairindex1.setTotalrepairprice("123456");
         adminrepairindex1.setRepairwork("R1234567");
         adminrepairindexRepository.saveAndFlush(adminrepairindex1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

         Adminrepairindex adminrepairindex2 = new Adminrepairindex();
         adminrepairindex2.setBreakdown("เปิดไม่ติด");
         adminrepairindex2.setTotalrepairprice("123456");
         adminrepairindex2.setRepairwork("R1234567");
         adminrepairindexRepository.saveAndFlush(adminrepairindex2);
         });
     }
}


















