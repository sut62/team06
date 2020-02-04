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
public class SexTest {

    private Validator validator;

    @Autowired
    private SexRepository sexRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5915040_testInsertSexSuccess(){
        Sex sex = new Sex();

        sex.setSex("ชายแท้");

        sex = sexRepository.saveAndFlush(sex);
        final Optional<Sex> found = sexRepository.findById(sex.getId());

        assertEquals("ชายแท้", found.get().getSex());
    }
    @Test
    void b5915040_testSexMustNotBeNull(){
        Sex sex = new Sex();

        sex.setSex(null);

        Set<ConstraintViolation<Sex>> result = validator.validate(sex);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Sex> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sex", v.getPropertyPath().toString());
    }

}
