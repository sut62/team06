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
        
        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
       

        receipt = receiptRepository.saveAndFlush(receipt);
        final Optional<Receipt> found = receiptRepository.findById(receipt.getId());
      
        assertEquals("TCG0000001", found.get().getReceiptNum());
        assertEquals("tanaka mana", found.get().getReceiptCusname());
        assertEquals("1234567890123", found.get().getReceiptCusident());
        assertEquals("0812345678", found.get().getReceiptCustel());
    }
     
    
    @Test
    void b5914760_testReceiptCusidentMustNotBeNull() {
        Receipt receipt = new Receipt();
        
        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident(null);
        receipt.setReceiptCustel("0812345678");
       

      
        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("receiptCusident", v.getPropertyPath().toString());
    }

   
    @Test
    void b5914760_testReceiptNumMustBeUnique() {
        // ÊÃéÒ§ num_id object
        Receipt receipt = new Receipt();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0812345678");
      

        receiptRepository.saveAndFlush(receipt);

        // ¤Ò´ËÇÑ§ÇèÒ DataIntegrityViolationException ¨Ð¶Ù¡ throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // ÊÃéÒ§ num_id object µÑÇ·Õè 2
            Receipt receipt2 = new Receipt();

            receipt2.setReceiptNum("TCG0000001");
            receipt2.setReceiptCusname("tanaka mana");
            receipt2.setReceiptCusident("1234567890123");
            receipt2.setReceiptCustel("0812345678");
          
            receiptRepository.saveAndFlush(receipt2);
        }
        );
    }

    @Test
    void b5914760_testSize_ReceiptCusident_MustNotBe12digit(){
        Receipt receipt = new Receipt();

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("123456789012");
        receipt.setReceiptCustel("0123456789");
      


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

        receipt.setReceiptNum("TCG0000001");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("12345678901234");
        receipt.setReceiptCustel("0123456789");
      


        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("size must be between 13 and 13",v.getMessage());
        assertEquals("receiptCusident",v.getPropertyPath().toString());
    } 

    @Test
    void b5914760_testReceiptNumMatPattern(){
        Receipt receipt = new Receipt();
      

        receipt.setReceiptNum("TC000000fdgfd");
        receipt.setReceiptCusname("tanaka mana");
        receipt.setReceiptCusident("1234567890123");
        receipt.setReceiptCustel("0123456789");
      

        

        Set<ConstraintViolation<Receipt>> result = validator.validate(receipt);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Receipt> v = result.iterator().next();
        assertEquals("must match \"[T][C][G]\\d{7}\"", v.getMessage());
        assertEquals("receiptNum", v.getPropertyPath().toString());
    }


}

