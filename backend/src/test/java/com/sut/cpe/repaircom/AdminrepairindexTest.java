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





@DataJpaTest
public class AdminrepairindexTest {
    
    private Validator validator;

    @Autowired
    private AdminrepairindexRepository adminrepairindexRepository;

    @Autowired
    private StatusrepairRepository statusrepairRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b5910168_testAdminrepairSuccess(){
        Adminrepairindex adminrepairindex = new Adminrepairindex();
        Date date = new Date();
        adminrepairindex.setBreakdown("เปิดไม่ติด");
        adminrepairindex.setTotalrepairprice(12345.00);
        adminrepairindex.setRepairwork("R1234567");
        adminrepairindex.setRepairDate(date);
        adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
        adminrepairindex.setBranch(branchRepository.findById(1L));
        adminrepairindex.setType(typeRepository.findById(1L));
        adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
        adminrepairindex.setCustomer(customerRepository.findById(1L));
        adminrepairindex.setBrand(brandRepository.findById(1L));


        adminrepairindex = adminrepairindexRepository.saveAndFlush(adminrepairindex);
        Optional<Adminrepairindex> found = adminrepairindexRepository.findById(adminrepairindex.getId());

        assertEquals("เปิดไม่ติด", found.get().getBreakdown());
        assertEquals(12345, 12345,found.get().getTotalrepairprice());
        assertEquals("R1234567", found.get().getRepairwork());
        assertEquals(date,found.get().getRepairDate());
        assertEquals(1L, found.get().getStatusrepair().getId());
        assertEquals(1L, found.get().getBranch().getId());
        assertEquals(1L, found.get().getType().getId());
        assertEquals(1L, found.get().getCreatedBy().getId());
        assertEquals(1L, found.get().getCustomer().getId());
        assertEquals(1L, found.get().getBrand().getId());

    }

    @Test 
    void b5910168_testAdminrepairBreakdownMustnotBenull(){
        Adminrepairindex adminrepairindex = new Adminrepairindex();
        Date date = new Date();
        adminrepairindex.setBreakdown(null);
        adminrepairindex.setTotalrepairprice(123456.00);
        adminrepairindex.setRepairwork("R1234567");
        adminrepairindex.setRepairDate(date);
        adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
        adminrepairindex.setBranch(branchRepository.findById(1L));
        adminrepairindex.setType(typeRepository.findById(1L));
        adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
        adminrepairindex.setCustomer(customerRepository.findById(1L));
        adminrepairindex.setBrand(brandRepository.findById(1L));
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
        
      }

     @Test
     void b5910168_testAdminrepairTestPatternBreakdown(){
         Adminrepairindex adminrepairindex = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex.setBreakdown("@#123$%^!@#$_");
         adminrepairindex.setTotalrepairprice(123456.00);
         adminrepairindex.setRepairwork("R1234567");
         adminrepairindex.setRepairDate(date);
         adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
         adminrepairindex.setBranch(branchRepository.findById(1L));
         adminrepairindex.setType(typeRepository.findById(1L));
         adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex.setCustomer(customerRepository.findById(1L));
         adminrepairindex.setBrand(brandRepository.findById(1L));
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must match \"[ก-์|A-z|\\s].+\"",v.getMessage());
        assertEquals("breakdown", v.getPropertyPath().toString());
     } 

     @Test
     void b5910168_testAdminrepairTestUniqueBreakdown() {
         
         Adminrepairindex adminrepairindex1 = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex1.setBreakdown("เปิดไม่ติด");
         adminrepairindex1.setTotalrepairprice(123456.00);
         adminrepairindex1.setRepairwork("R1234567");
         adminrepairindex1.setRepairDate(date);
         adminrepairindex1.setStatusrepair(statusrepairRepository.findById(1L));
         adminrepairindex1.setBranch(branchRepository.findById(1L));
         adminrepairindex1.setType(typeRepository.findById(1L));
         adminrepairindex1.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex1.setCustomer(customerRepository.findById(1L));
         adminrepairindex1.setBrand(brandRepository.findById(1L));

         adminrepairindexRepository.saveAndFlush(adminrepairindex1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

         Adminrepairindex adminrepairindex2 = new Adminrepairindex();
         Date date2 = new Date();
         adminrepairindex2.setBreakdown("เปิดไม่ติด");
         adminrepairindex2.setTotalrepairprice(123456.00);
         adminrepairindex2.setRepairwork("R1234567");
         adminrepairindex2.setRepairDate(date2);
         adminrepairindex2.setStatusrepair(statusrepairRepository.findById(1L));
         adminrepairindex2.setBranch(branchRepository.findById(1L));
         adminrepairindex2.setType(typeRepository.findById(1L));
         adminrepairindex2.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex2.setCustomer(customerRepository.findById(1L));
         adminrepairindex2.setBrand(brandRepository.findById(1L));
         adminrepairindexRepository.saveAndFlush(adminrepairindex2);
         });
     }
    @Test
     void b5910168_testAdminrepairRepairworkPatterncheck(){
        Adminrepairindex admin = new Adminrepairindex();
        Date date = new Date();
        admin.setBreakdown("เปิดไม่ติด");
        admin.setTotalrepairprice(123456.00);
        admin.setRepairwork("B1234567");
        admin.setRepairDate(date);
        admin.setStatusrepair(statusrepairRepository.findById(1L));
        admin.setBranch(branchRepository.findById(1L));
        admin.setType(typeRepository.findById(1L));
        admin.setCreatedBy(employeeRepository.findById(1L));
        admin.setCustomer(customerRepository.findById(1L));
        admin.setBrand(brandRepository.findById(1L));

        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(admin);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must match \"[R]\\d{7}\"", v.getMessage());
        assertEquals("repairwork", v.getPropertyPath().toString());
     } 
    @Test
     void b5910168_testAdminrepairTotalpriceNonNegativeValue(){
        Adminrepairindex admin = new Adminrepairindex();
        Date date = new Date();
        admin.setBreakdown("เปิดไม่ติด");
        admin.setTotalrepairprice(-12.00);
        admin.setRepairwork("R1234567");
        admin.setRepairDate(date);
        admin.setStatusrepair(statusrepairRepository.findById(1L));
        admin.setBranch(branchRepository.findById(1L));
        admin.setType(typeRepository.findById(1L));
        admin.setCreatedBy(employeeRepository.findById(1L));
        admin.setCustomer(customerRepository.findById(1L));
        admin.setBrand(brandRepository.findById(1L));
        Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(admin);

        assertEquals(1, result.size());

        ConstraintViolation<Adminrepairindex> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("totalrepairprice", v.getPropertyPath().toString());
     }
     @Test
     void b5910168_testAdminrepairTestRepairworkmustbeunique() {
         
         Adminrepairindex adminrepairindex1 = new Adminrepairindex();
         Date date = new Date();
         adminrepairindex1.setBreakdown("เปิดไม่ติด");
         adminrepairindex1.setTotalrepairprice(123456.00);
         adminrepairindex1.setRepairwork("R1234567");
         adminrepairindex1.setRepairDate(date);
         adminrepairindex1.setStatusrepair(statusrepairRepository.findById(1L));
         adminrepairindex1.setBranch(branchRepository.findById(1L));
         adminrepairindex1.setType(typeRepository.findById(1L));
         adminrepairindex1.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex1.setCustomer(customerRepository.findById(1L));
         adminrepairindex1.setBrand(brandRepository.findById(1L));

         adminrepairindexRepository.saveAndFlush(adminrepairindex1);
 
     
         assertThrows(DataIntegrityViolationException.class, () -> {

         Adminrepairindex adminrepairindex2 = new Adminrepairindex();
         Date date2 = new Date();
         adminrepairindex2.setBreakdown("ไฟเข้าจอไม่ติด");
         adminrepairindex2.setTotalrepairprice(123456.00);
         adminrepairindex2.setRepairwork("R1234567");
         adminrepairindex2.setRepairDate(date2);
         adminrepairindex2.setStatusrepair(statusrepairRepository.findById(1L));
         adminrepairindex2.setBranch(branchRepository.findById(1L));
         adminrepairindex2.setType(typeRepository.findById(1L));
         adminrepairindex2.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex2.setCustomer(customerRepository.findById(1L));
         adminrepairindex2.setBrand(brandRepository.findById(1L));
         adminrepairindexRepository.saveAndFlush(adminrepairindex2);
         });
     }
     @Test 
     void b5910168_testAdminrepairLinkwStatusMustnotBenull(){
         Adminrepairindex adminrepairindex = new Adminrepairindex();
         Date date = new Date();
    
         
         adminrepairindex.setBreakdown("เปิดไม่ติด");
         adminrepairindex.setTotalrepairprice(123456.00);
         adminrepairindex.setRepairwork("R1234567");
         adminrepairindex.setRepairDate(date);
         adminrepairindex.setStatusrepair(null);
         adminrepairindex.setBranch(branchRepository.findById(1L));
         adminrepairindex.setType(typeRepository.findById(1L));
         adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
         adminrepairindex.setCustomer(customerRepository.findById(1L));
         adminrepairindex.setBrand(brandRepository.findById(1L));
         Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
 
         assertEquals(1, result.size());
 
         ConstraintViolation<Adminrepairindex> v = result.iterator().next();
         assertEquals("must not be null",v.getMessage());
         assertEquals("statusrepair", v.getPropertyPath().toString());
         
       }
       @Test 
       void b5910168_testAdminrepairLinkwBranchMustnotBenull(){
           Adminrepairindex adminrepairindex = new Adminrepairindex();
           Date date = new Date();
      
           
           adminrepairindex.setBreakdown("เปิดไม่ติด");
           adminrepairindex.setTotalrepairprice(123456.00);
           adminrepairindex.setRepairwork("R1234567");
           adminrepairindex.setRepairDate(date);
           adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
           adminrepairindex.setBranch(null);
           adminrepairindex.setType(typeRepository.findById(1L));
           adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
           adminrepairindex.setCustomer(customerRepository.findById(1L));
           adminrepairindex.setBrand(brandRepository.findById(1L));
           Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
   
           assertEquals(1, result.size());
   
           ConstraintViolation<Adminrepairindex> v = result.iterator().next();
           assertEquals("must not be null",v.getMessage());
           assertEquals("branch", v.getPropertyPath().toString());
           
         }

         @Test 
         void b5910168_testAdminrepairLinkwTypeMustnotBenull(){
             Adminrepairindex adminrepairindex = new Adminrepairindex();
             Date date = new Date();
        
             
             adminrepairindex.setBreakdown("เปิดไม่ติด");
             adminrepairindex.setTotalrepairprice(123456.00);
             adminrepairindex.setRepairwork("R1234567");
             adminrepairindex.setRepairDate(date);
             adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
             adminrepairindex.setBranch(branchRepository.findById(1L));
             adminrepairindex.setType(null);
             adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
             adminrepairindex.setCustomer(customerRepository.findById(1L));
             adminrepairindex.setBrand(brandRepository.findById(1L));
             Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
     
             assertEquals(1, result.size());
     
             ConstraintViolation<Adminrepairindex> v = result.iterator().next();
             assertEquals("must not be null",v.getMessage());
             assertEquals("type", v.getPropertyPath().toString());
             
           }
           @Test 
           void b5910168_testAdminrepairLinkwEmployeeMustnotBenull(){
               Adminrepairindex adminrepairindex = new Adminrepairindex();
               Date date = new Date();
          
               
               adminrepairindex.setBreakdown("เปิดไม่ติด");
               adminrepairindex.setTotalrepairprice(123456.00);
               adminrepairindex.setRepairwork("R1234567");
               adminrepairindex.setRepairDate(date);
               adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
               adminrepairindex.setBranch(branchRepository.findById(1L));
               adminrepairindex.setType(typeRepository.findById(1L));
               adminrepairindex.setCreatedBy(null);
               adminrepairindex.setCustomer(customerRepository.findById(1L));
               adminrepairindex.setBrand(brandRepository.findById(1L));

               Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
       
               assertEquals(1, result.size());
       
               ConstraintViolation<Adminrepairindex> v = result.iterator().next();
               assertEquals("must not be null",v.getMessage());
               assertEquals("createdBy", v.getPropertyPath().toString());
               
             }
             
             @Test 
             void b5910168_testAdminrepairLinkwCustomerMustnotBenull(){
                 Adminrepairindex adminrepairindex = new Adminrepairindex();
                 Date date = new Date();
            
                 
                 adminrepairindex.setBreakdown("เปิดไม่ติด");
                 adminrepairindex.setTotalrepairprice(123456.00);
                 adminrepairindex.setRepairwork("R1234567");
                 adminrepairindex.setRepairDate(date);
                 adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
                 adminrepairindex.setBranch(branchRepository.findById(1L));
                 adminrepairindex.setType(typeRepository.findById(1L));
                 adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
                 adminrepairindex.setCustomer(null);
                 adminrepairindex.setBrand(brandRepository.findById(1L));
                 Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
         
                 assertEquals(1, result.size());
         
                 ConstraintViolation<Adminrepairindex> v = result.iterator().next();
                 assertEquals("must not be null",v.getMessage());
                 assertEquals("customer", v.getPropertyPath().toString());
                 
               }
               @Test 
               void b5910168_testAdminrepairLinkwBrandMustnotBenull(){
                   Adminrepairindex adminrepairindex = new Adminrepairindex();
                   Date date = new Date();
              
                   
                   adminrepairindex.setBreakdown("เปิดไม่ติด");
                   adminrepairindex.setTotalrepairprice(123456.00);
                   adminrepairindex.setRepairwork("R1234567");
                   adminrepairindex.setRepairDate(date);
                   adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
                   adminrepairindex.setBranch(branchRepository.findById(1L));
                   adminrepairindex.setType(typeRepository.findById(1L));
                   adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
                   adminrepairindex.setCustomer(customerRepository.findById(1L));
                   adminrepairindex.setBrand(null);
                   Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
           
                   assertEquals(1, result.size());
           
                   ConstraintViolation<Adminrepairindex> v = result.iterator().next();
                   assertEquals("must not be null",v.getMessage());
                   assertEquals("brand", v.getPropertyPath().toString());
                   
                 }
                 
                 @Test 
                 void b5910168_testAdminrepairTotalrepairpriceMustnotBenull(){
                     Adminrepairindex adminrepairindex = new Adminrepairindex();
                     Date date = new Date();
                     adminrepairindex.setBreakdown("จอติดแต่ไม่บูท");
                     adminrepairindex.setTotalrepairprice(null);
                     adminrepairindex.setRepairwork("R1234567");
                     adminrepairindex.setRepairDate(date);
                     adminrepairindex.setStatusrepair(statusrepairRepository.findById(1L));
                     adminrepairindex.setBranch(branchRepository.findById(1L));
                     adminrepairindex.setType(typeRepository.findById(1L));
                     adminrepairindex.setCreatedBy(employeeRepository.findById(1L));
                     adminrepairindex.setCustomer(customerRepository.findById(1L));
                     adminrepairindex.setBrand(brandRepository.findById(1L));
                     Set<ConstraintViolation<Adminrepairindex>> result = validator.validate(adminrepairindex);
             
                     assertEquals(1, result.size());
             
                     ConstraintViolation<Adminrepairindex> v = result.iterator().next();
                     assertEquals("must not be null",v.getMessage());
                     assertEquals("totalrepairprice", v.getPropertyPath().toString());
                     
                   }
}


















