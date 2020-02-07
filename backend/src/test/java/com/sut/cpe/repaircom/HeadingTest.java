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
public class HeadingTest {

    private Validator validator;

    @Autowired
    private HeadingRepository headingRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5907113_testInsertHeadingSuccess(){
        Heading heading = new Heading();

        heading.setHeadingName("สอบถามข้อมูลเกี่ยวการแจ้งซ่อม");

        heading = headingRepository.saveAndFlush(heading);
        final Optional<Heading> found = headingRepository.findById(heading.getId());

        assertEquals("สอบถามข้อมูลเกี่ยวการแจ้งซ่อม", found.get().getHeadingName());
    }

    

    @Test
    void b5907113_testHeadingMustNotBeNull(){
        Heading heading = new Heading();

        heading.setHeadingName(null);

        Set<ConstraintViolation<Heading>> result = validator.validate(heading);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Heading> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("headingName", v.getPropertyPath().toString());
    }

}
