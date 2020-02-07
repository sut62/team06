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






@DataJpaTest
public class AddproductTest {
    
    private Validator validator;

    @Autowired
    private AddproductRepository addproductRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private BranchRepository branchRepository;

    
    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5916962_testAddproductSuccess(){
        Addproduct addproduct = new Addproduct();
      
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
        

        addproduct = addproductRepository.saveAndFlush(addproduct);
        final Optional<Addproduct> found = addproductRepository.findById(addproduct.getId());

        assertEquals("20200001", found.get().getProid());
        assertEquals("MSI RAM", found.get().getProductname());
        assertEquals("8GB", found.get().getDescription());
        assertEquals(800, found.get().getPrice());
        assertEquals(1L, found.get().getType().getId());
        assertEquals(1L, found.get().getBrand().getId());
        assertEquals(1L, found.get().getPart().getId());
        assertEquals(1L, found.get().getBranch().getId());
        
    }
    @Test
    void b5916962_testProidPattern() {
        Addproduct addproduct = new Addproduct();

        addproduct.setProid("20200001grgrt");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
        
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must match \"\\d{8}\"", v.getMessage());
        assertEquals("proid", v.getPropertyPath().toString());
    }
    

    @Test
    void b5916962_testProductIDMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid(null);
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));

      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("proid", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testProductnameMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname(null);
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));

      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("productname", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testDescriptionMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription(null);
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("description", v.getPropertyPath().toString());
    }
    @Test
     void b5910168_testPriceNonNegativeValue(){
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(-12);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);


        assertEquals(1, result.size());

        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
     }
     @Test
    void b5916962_testTypeMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(null);
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testBrandMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(null);
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("brand", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testPartMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(null);
        addproduct.setBranch(branchRepository.findById(1L));
      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("part", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testBranchMustNotBeNull() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("8GB");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(null);
      
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Addproduct> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("branch", v.getPropertyPath().toString());
    }
    @Test
    void b5916962_testDescriptionSize() {
        Addproduct addproduct = new Addproduct();
        
        addproduct.setProid("20200001");
        addproduct.setProductname("MSI RAM");
        addproduct.setDescription("");
        addproduct.setPrice(800);
        addproduct.setType(typeRepository.findById(1L));
        addproduct.setBrand(brandRepository.findById(1L));
        addproduct.setPart(partRepository.findById(1L));
        addproduct.setBranch(branchRepository.findById(1L));
        Set<ConstraintViolation<Addproduct>> result = validator.validate(addproduct);

        assertEquals(1, result.size());

       ConstraintViolation<Addproduct> v = result.iterator().next();
       assertEquals("size must be between 1 and 2000",v.getMessage());
       assertEquals("description",v.getPropertyPath().toString());
    }
    @Test
     void b5916962_testProidTestUnique() {
         
        Addproduct a1 = new Addproduct();
        a1.setProid("20200001");
        a1.setProductname("MSI RAM");
        a1.setDescription("8GB");
        a1.setPrice(800);
        a1.setType(typeRepository.findById(1L));
        a1.setBrand(brandRepository.findById(1L));
        a1.setPart(partRepository.findById(1L));
        a1.setBranch(branchRepository.findById(1L));
        addproductRepository.saveAndFlush(a1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

           
         Addproduct a2 = new Addproduct();

         a2.setProid("20200001");
         a2.setProductname("MSI RAM");
         a2.setDescription("8GB");
         a2.setPrice(800);
         a2.setType(typeRepository.findById(1L));
         a2.setBrand(brandRepository.findById(1L));
         a2.setPart(partRepository.findById(1L));
         a2.setBranch(branchRepository.findById(1L));
         addproductRepository.saveAndFlush(a2);
         });
     }

    

}


















