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
        adminrepairindex.setPartn2("cpu");
        adminrepairindex.setPartn3("gpu");
        adminrepairindex.setPartn4("ram");
        adminrepairindex.setPartn5("psu");

        adminrepairindex = adminrepairindexRepository.saveAndFlush(adminrepairindex);
        final Optional<Adminrepairindex> found = adminrepairindexRepository.findById(adminrepairindex.getId());

        assertEquals("เปิดไม่ติด", found.get().getBreakdown());
        assertEquals("cpu", found.get().getPartn2());
        assertEquals("gpu", found.get().getPartn3());
        assertEquals("ram", found.get().getPartn4());
        assertEquals("psu", found.get().getPartn5());
    }

}


















