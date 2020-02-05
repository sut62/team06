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

import ch.qos.logback.core.status.Status;

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
public class StatusrepairTest {
    private Validator validator;

    @Autowired
    private StatusrepairRepository statusrepairRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5910168_StatusrepairtestSuccess(){
        Statusrepair statusrepair = new Statusrepair();

        statusrepair.setCurrentstatus("กำลังดำเนินการ");

        statusrepair = statusrepairRepository.saveAndFlush(statusrepair);
        Optional<Statusrepair> found = statusrepairRepository.findById(statusrepair.getId());

        assertEquals("กำลังดำเนินการ", found.get().getCurrentstatus());
    }

    @Test
    void b5910168_StatusrepairCurrentStatusFitwithPattarninDataloader(){
        Statusrepair statusrepair = new Statusrepair();

        statusrepair.setCurrentstatus("ซ่อมได้แต่ไม่ซ่อม");
        Set<ConstraintViolation<Statusrepair>> result = validator.validate(statusrepair);
        
        assertEquals(1,result.size());

        ConstraintViolation<Statusrepair> v = result.iterator().next();
        assertEquals("must match \"รับเรื่องแล้ว|กำลังดำเนินการ|รออะไหล่|ดำเนินการเสร็จสิ้น\"",v.getMessage());
        assertEquals("currentstatus",v.getPropertyPath().toString());


    }
    @Test
    void b5910168_StatusrepairCurrentStatusMustNotBeNull(){
        Statusrepair statusrepair = new Statusrepair();

        statusrepair.setCurrentstatus(null);
        Set<ConstraintViolation<Statusrepair>> result = validator.validate(statusrepair);
        
        assertEquals(1,result.size());

        ConstraintViolation<Statusrepair> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("currentstatus",v.getPropertyPath().toString());


    }
}
    