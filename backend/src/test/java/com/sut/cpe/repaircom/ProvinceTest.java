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
public class ProvinceTest {

    private Validator validator;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5915040_testInsertProvinceSuccess(){
        Province province = new Province();

        province.setProvince("ขอนแก่น");

        province = provinceRepository.saveAndFlush(province);
        final Optional<Province> found = provinceRepository.findById(province.getId());

        assertEquals("ขอนแก่น", found.get().getProvince());
    }
    @Test
    void b5915040_testProvinceMustNotBeNull(){
        Province province = new Province();

        province.setProvince(null);

        Set<ConstraintViolation<Province>> result = validator.validate(province);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());
    }

}
