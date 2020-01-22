package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Fix;
import com.sut.cpe.repaircom.repository.FixRepository;;
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

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5907397_testFixSuccess(){
        Fix fix = new Fix();
      
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");
       

        fix = fixRepository.saveAndFlush(fix);
        final Optional<Fix> found = fixRepository.findById(fix.getId());
      
        assertEquals("จอดับบ่อยๆ", found.get().getFixname());
        assertEquals("00012020", found.get().getQueue());
    
    }  

    @Test
    void b5907397_testQueueMustNotBeNull() {
        Fix fix = new Fix();
        
        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue(null);
     
      
        Set<ConstraintViolation<Fix>> result = validator.validate(fix);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Fix> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("queue", v.getPropertyPath().toString());
    }

    @Test
    void b5907397_testFixnameSize() {
       Fix fix = new Fix();

       fix.setFixname("จอดับบ่อยๆ");
       fix.setQueue("00012020");

      fix = fixRepository.saveAndFlush(fix);

        Optional<Fix> found = fixRepository.findById(fix.getId());
        assertEquals("จอดับบ่อยๆ", found.get().getFixname());
    }

    @Test
    void b5907397_testQueuePattern() {
        Fix fix = new Fix();

        fix.setFixname("จอดับบ่อยๆ");
        fix.setQueue("00012020");

        fix = fixRepository.saveAndFlush(fix);

        Optional<Fix> found = fixRepository.findById(fix.getId());
        assertEquals("00012020", found.get().getQueue());
    }
   
    
}

