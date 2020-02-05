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


import com.sut.cpe.repaircom.entity.Receipt;
import com.sut.cpe.repaircom.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@DataJpaTest
public class ReceiptTest {

    private Validator validator;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AdminrepairindexRepository adminrepairindexRepository;



    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5914760_testReceiptSuccess(){
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));

  
      
          

        receipt = receiptRepository.saveAndFlush(receipt);
        final Optional<Receipt> found = receiptRepository.findById(receipt.getId());
      
        assertEquals("TCG0000001", found.get().getReceiptNum());
        assertEquals("tanaka mana", found.get().getReceiptCusname());
        assertEquals("1234567890123", found.get().getReceiptCusident());
        assertEquals("0812345678", found.get().getReceiptCustel());
        assertEquals(date,found.get().getReceiptDate());
        assertEquals(1, found.get().getBranch().getId());
        assertEquals(1, found.get().getEmployee().getId());
        assertEquals(1, found.get().getType().getId());
        assertEquals(1, found.get().getAdminrepairindex().getId());
       
    }

    
    @Test
    void b5914760_testReceiptNum_MustBeUnique() {
      
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

       

        receiptRepository.saveAndFlush(receipt);

       
        assertThrows(DataIntegrityViolationException.class, () -> {
            
             Receipt receipt2 = new Receipt();
             Date date2 = new Date();

        receipt2.setReceiptNum("TCG0000001");
        receipt2.setReceiptCusname("tanaka mana");
        receipt2.setReceiptCusident("1234567890123");
        receipt2.setReceiptCustel("0812345678");
        receipt2.setReceiptDate(date2);
        receipt2.setBranch(branchRepository.findById(1));
        receipt2.setEmployee(employeeRepository.findById(1));
        receipt2.setType(typeRepository.findById(1));
        receipt2.setAdminrepairindex(adminrepairindexRepository.findById(1));
        
          
            receiptRepository.saveAndFlush(receipt2);
        }
        );
    }

    @Test
    void b5914760_test_ReceiptNum_NotMatch_Pattern(){
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCF45784");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
      
           
        
        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must match \"[T][C][G]\\d{7}\"", v.getMessage());
        assertEquals("receiptNum", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceipCustel_NotMatch_Pattern(){
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678123456");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
      
           
        
        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("receiptCustel", v.getPropertyPath().toString());
    }


     

    @Test
    void b5914760_testSize_ReceiptCusident_MustNotBe12digit(){
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("123456789012");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));


        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("size must be between 13 and 13",v.getMessage());
        assertEquals("receiptCusident",v.getPropertyPath().toString());
    } 

    @Test
    void b5914760_testSize_ReceiptCusident_MustNotBe14digit(){
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("12345678901234");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
      


        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("size must be between 13 and 13",v.getMessage());
        assertEquals("receiptCusident",v.getPropertyPath().toString());
    } 

       
    @Test
    void b5914760_test_ReceiptNum_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum(null);
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptNum", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptCusname_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname(null);
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptCusname", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptCusident_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident(null);
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptCusident", v.getPropertyPath().toString());
    }


    @Test
    void b5914760_test_ReceiptCustel_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel(null);
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptCustel", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptDate_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(null);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptDate", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptLinkBranch_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(null);
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("branch", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptLinkEmployee_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(null);
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void b5914760_test_ReceiptLinkType_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(null);
        receipt.setAdminrepairindex(adminrepairindexRepository.findById(1));
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

    
    @Test
    void b5914760_test_ReceiptLinkAdminrepairindex_MustNotBeNull() {
        Receipt receipt = new Receipt();
        Date date = new Date();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
        receipt.setReceiptDate(date);
        receipt.setBranch(branchRepository.findById(1));
        receipt.setEmployee(employeeRepository.findById(1));
        receipt.setType(typeRepository.findById(1));
        receipt.setAdminrepairindex(null);
        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("adminrepairindex", v.getPropertyPath().toString());
    }

}

