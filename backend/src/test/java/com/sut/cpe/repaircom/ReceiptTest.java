package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Receipt;
import com.sut.cpe.repaircom.repository.ReceiptRepository;
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
public class ReceiptTest {

    private Validator validator;

    @Autowired
    private ReceiptRepository receiptRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5914760_testReceiptSuccess(){
        Receipt receipt = new Receipt();
      
        receipt.setReceiptPrice("1580");
        receipt.setProR2("cpu");
        receipt.setProR3("gpu");
        receipt.setProR4("ram");
        receipt.setProR5("psu");

        receipt = receiptRepository.saveAndFlush(receipt);
        final Optional<Receipt> found = receiptRepository.findById(receipt.getId());
      
        assertEquals("1580", found.get().getReceiptPrice());
        assertEquals("cpu", found.get().getProR2());
        assertEquals("gpu", found.get().getProR3());
        assertEquals("ram", found.get().getProR4());
        assertEquals("psu", found.get().getProR5());
    }
}

/*
        @Test
        void b5914760_testReceiptNotBeNull() {
        Receipt receipt = new Receipt();
        receipt.setReceiptPrice(null);
        receipt.setProR2("cpu");
        receipt.setProR3("gpu");
        receipt.setProR4("ram");
        receipt.setProR5("psu");

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptPrice", v.getPropertyPath().toString());
    }


  /*  @Test
    void testStudentIdIdMustNotBeAChar() {
        Person person = new Person();
        person.setStudentId("A1234567"); // 12 digits
        person.setStudentName("thanaphon");
        person.setStudentLastn("puputta");
        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must match \"\\d{7}\"", v.getMessage());
        assertEquals("studentId", v.getPropertyPath().toString());
    }

    @Test
    void testStudentIdIdMustNotBeCChar() {
        Person person = new Person();
        person.setStudentId("C1234567"); // 12 digits
        person.setStudentName("thanaphon");
        person.setStudentLastn("puputta");
        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must match \"\\d{7}\"", v.getMessage());
        assertEquals("studentId", v.getPropertyPath().toString());
    }


}

*/