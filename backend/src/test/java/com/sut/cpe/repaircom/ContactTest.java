package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Contact;
import com.sut.cpe.repaircom.repository.ContactRepository;
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
public class ContactTest {

    private Validator validator;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b5907113_testContactSuccess(){
        Contact contact = new Contact();

        contact.setDetail("เพิ่ม RAM ประมานราคากี่บาท");
        contact.setEmail("dom@gmail.com");
        contact.setPhone("0611308986");
    
        contact = contactRepository.saveAndFlush(contact);
        final Optional<Contact> found = contactRepository.findById(contact.getId());

        assertEquals("เพิ่ม RAM ประมานราคากี่บาท", found.get().getDetail());
        assertEquals("dom@gmail.com", found.get().getEmail());
        assertEquals("0611308986", found.get().getPhone());
    }


    @Test
    void b6000035_testEmailForm(){
        Contact contact = new Contact();

        contact.setPhone("0123456789");
        contact.setEmail("testmail");
        contact.setDetail("ราคาเปลี่ยนจอ");


        Set<ConstraintViolation<Contact>> result = validator.validate(contact);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Contact> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }


     @Test
     void b5907113_testEmailMustNotBeNull() {
        Contact contact = new Contact();

        contact.setDetail("เพิ่ม RAM ประมานราคากี่บาท");
        contact.setEmail(null);
        contact.setPhone("0611308986");

      
         Set<ConstraintViolation<Contact>> result = validator.validate(contact);

          //result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<Contact> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("email", v.getPropertyPath().toString());
     }

    
    @Test
    void b5907113_testSize_Detail(){
        Contact contact = new Contact();

        contact.setDetail("a");
        contact.setEmail("dom@gmail.com");
        contact.setPhone("0611308986");


        Set<ConstraintViolation<Contact>> result = validator.validate(contact);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Contact> v = result.iterator().next();
        assertEquals("size must be between 2 and 2000",v.getMessage());
        assertEquals("detail",v.getPropertyPath().toString());
    } 



    @Test
    void b5907113_test_Phone_Pattern(){
        Contact contact = new Contact();

        contact.setDetail("เพิ่ม RAM ประมานราคากี่บาท");
        contact.setEmail("dom@gmail.com");
        contact.setPhone("1");
      

        
        Set<ConstraintViolation<Contact>> result = validator.validate(contact);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Contact> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phone", v.getPropertyPath().toString());
    
    }


}



