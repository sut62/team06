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
public class BranchTest {

    private Validator validator;

    @Autowired
    private BranchRepository branchRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5916962_testInsertBranchSuccess(){
        Branch branch = new Branch();

        branch.setBranchname("สาขาปราจีนบุรี");

        branch = branchRepository.saveAndFlush(branch);
        final Optional<Branch> found = branchRepository.findById(branch.getId());

        assertEquals("สาขาปราจีนบุรี", found.get().getBranchname());
    }
    @Test
    void b5916962_testBranchMustNotBeNull(){
        Branch branch = new Branch();

        branch.setBranchname(null);

        Set<ConstraintViolation<Branch>> result = validator.validate(branch);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Branch> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("branchname", v.getPropertyPath().toString());
    }

}
