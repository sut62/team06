package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Addproduct;
import com.sut.cpe.repaircom.repository.AddproductRepository;
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
public class AddproductTest {
    
    private Validator validator;

    @Autowired
    private AddproductRepository addproductRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5916962_testProidPattern() {
        Addproduct addproduct = new Addproduct();

        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        

        addproduct = addproductRepository.saveAndFlush(addproduct);

        Optional<Addproduct> found = addproductRepository.findById(addproduct.getId());
        assertEquals("20200001", found.get().getProid());
    }
    @Test
    void b5916962_testAddproductSuccess(){
        Addproduct addproduct = new Addproduct();
      
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        

        addproduct = addproductRepository.saveAndFlush(addproduct);
        final Optional<Addproduct> found = addproductRepository.findById(addproduct.getId());
        assertEquals("20200001", found.get().getProid());
        assertEquals("MSI RAM", found.get().getProductname());
        assertEquals("8GB", found.get().getDescription());
        assertEquals(800, found.get().getPrice());
        
    }
    @Test
    void b5916962_testProductnameMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname(null);
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);

      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("productname", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testDescriptionSize() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);

        addproduct = addproductRepository.saveAndFlush(addproduct);

        Optional<Addproduct> found = addproductRepository.findById(addproduct.getId());
        assertEquals("8GB", found.get().getDescription());
    }
    @Test
     void b5916962_testProidTestUnique() {
         
        Addproduct a1 = new Addproduct();
        a1.setProid("20200001");
        a1.setProductname("MSI RAM");
        a1.setDescription("8GB");
        a1.setPrice(800);
        addproductRepository.saveAndFlush(a1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

           
         Addproduct a2 = new Addproduct();

         a2.setProid("20200001");
         a2.setProductname("MSI RAM");
         a2.setDescription("8GB");
         a2.setPrice(800);
         addproductRepository.saveAndFlush(a2);
         });
     }

    

}


















