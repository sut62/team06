package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

@SpringBootApplication
public class RepaircomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepaircomApplication.class, args);

    }

    @Bean
    ApplicationRunner init(EmployeeRepository employeeRepository,ProvinceRepository provinceRepository,SexRepository sexRepository,AddproductRepository addproductRepository,TypeRepository typeRepository,BrandRepository brandRepository,PartRepository partRepository,BranchRepository branchRepository,FixtypeRepository fixtypeRepository,CustomerRepository customerRepository,StatusrepairRepository statusrepairRepository,HeadingRepository headingRepository,CommunicationRepository communicationRepository,MachinecolorRepository machinecolorRepository,ProfileloginRepository profileloginRepository) {
        return args -> {
            Stream.of("กอกา มอนี", "มาม้า คึกคัก", "สอเสือ เขี้ยวใหญ่").forEach(name -> {
                Employee employee = new Employee();
                employee.setEmpName(name);
                employeeRepository.save(employee);
            });

            Stream.of("ชาย", "หญิง").forEach(name -> {
                Sex sex = new Sex();
                sex.setSex(name);
                sexRepository.save(sex);
            });

            Stream.of("นครราชสีมา", "บุรีรัมย์").forEach(name -> {
                Province province = new Province();
                province.setProvince(name);
                provinceRepository.save(province);
            });

            Stream.of("มนัสวัน").forEach(name -> {
                Customer customer = new Customer();
                customer.setCusName("มนัส วันดี");
                customer.setIdentification("1319800215244");
                try {
                    customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
                }
                catch (Exception e) {
                    System.out.println("wrong time format");
                }
                customer.setAge(12);
                customer.setAddress("หัวเห็ด");
                customer.setSubDistrict("บางนา");
                customer.setDistrict("บางพลี");
                customer.setTel("0321254551");
                customer.setEmail("caco@gmail.com");
                customerRepository.save(customer);
            });

            Stream.of("มนูญ").forEach(name -> {
                Customer customer = new Customer();
                customer.setCusName("มนูญ สิงโต");
                customer.setIdentification("1234567890123");
                try {
                    customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2010-05-20"));
                }
                catch (Exception e) {
                    System.out.println("wrong time format");
                }
                customer.setAge(50);
                customer.setAddress("หัวเห็ด");
                customer.setSubDistrict("บางนา");
                customer.setDistrict("บางพลี");
                customer.setTel("0212549548");
                customer.setEmail("babo@hotmail.com");
                customerRepository.save(customer);
            });

            typeRepository.save(new Type("PC"));
            typeRepository.save(new Type("Notebook"));

            brandRepository.save(new Brand("RAZER"));
            brandRepository.save(new Brand("STEEL-SERIES"));
            brandRepository.save(new Brand("MSI"));
            brandRepository.save(new Brand("INTEL"));
            brandRepository.save(new Brand("ACER"));

            partRepository.save(new Part("KEYBOARD"));
            partRepository.save(new Part("MONITOR"));
            partRepository.save(new Part("MOUSE"));
            partRepository.save(new Part("CPU"));
            partRepository.save(new Part("RAM"));
            partRepository.save(new Part("no repair"));



            branchRepository.save(new Branch("สาขาจังหวัดกรุงเทพมหานคร"));
            branchRepository.save(new Branch("สาขาจังหวัดปราจีนบุรี"));
            branchRepository.save(new Branch("สาขาจังหวัดนครราชสีมา"));
            branchRepository.save(new Branch("สาขาจังหวัดปัตตานี"));
            branchRepository.save(new Branch("สาขาจังหวัดยะลา"));
            fixtypeRepository.save(new Fixtype("Computer"));
            fixtypeRepository.save(new Fixtype("Notebook"));


            branchRepository.save(new Branch("ห้างโคราช"));
            branchRepository.save(new Branch("มทส"));
            brandRepository.save(new Brand("acer"));
            brandRepository.save(new Brand("msi"));


            statusrepairRepository.save(new Statusrepair("รับเรื่องแล้ว"));
            statusrepairRepository.save(new Statusrepair("กำลังดำเนินการ"));
            statusrepairRepository.save(new Statusrepair("รออะไหล่"));
            statusrepairRepository.save(new Statusrepair("ดำเนินการเสร็จสิ้น"));

            communicationRepository.save(new Communication("ติดต่อกลับทาง อีเมล"));//ระบบติดต่อสอบถาม
            communicationRepository.save(new Communication("ติดต่อกลับทาง โทรศัพท์"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามข้อมูลเกี่ยวการแจ้งซ่อม"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามข้อมูลอาการและปัญหาคอมพิวเตอร์"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("แจ้งเรื่องร้องเรียน"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามเรื่องอื่นๆ"));//ระบบติดต่อสอบถาม

            


            machinecolorRepository.save(new Machinecolor("Red"));
            machinecolorRepository.save(new Machinecolor("Green"));
            machinecolorRepository.save(new Machinecolor("Blue"));
            machinecolorRepository.save(new Machinecolor("White"));
            machinecolorRepository.save(new Machinecolor("Pink"));
            machinecolorRepository.save(new Machinecolor("Black"));
            machinecolorRepository.save(new Machinecolor("Gray"));
            machinecolorRepository.save(new Machinecolor("Silver"));

            profileloginRepository.save(new Profilelogin("customer","12345678"));
            profileloginRepository.save(new Profilelogin("fix","12345678"));
            profileloginRepository.save(new Profilelogin("admin","12345678"));
            profileloginRepository.save(new Profilelogin("addproduct","12345678"));
            profileloginRepository.save(new Profilelogin("receipt","12345678"));
            profileloginRepository.save(new Profilelogin("contact","12345678"));
            
            

        };
    }
}
