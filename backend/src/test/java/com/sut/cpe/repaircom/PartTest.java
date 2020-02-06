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
public class PartTest {

    private Validator validator;

    @Autowired
    private PartRepository partRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5916962_testInsertPartSuccess(){
        Part part = new Part();

        part.setPartname("RAM");

        part = partRepository.saveAndFlush(part);
        final Optional<Part> found = partRepository.findById(part.getId());

        assertEquals("RAM", found.get().getPartname());
    }
    @Test
    void b5916962_testPartMustNotBeNull(){
        Part part = new Part();

        part.setPartname(null);

        Set<ConstraintViolation<Part>> result = validator.validate(part);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Part> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("partname", v.getPropertyPath().toString());
    }

}
