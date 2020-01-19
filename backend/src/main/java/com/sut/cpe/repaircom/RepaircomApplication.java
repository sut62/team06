package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.*;
import com.sut.cpe.repaircom.entity.Province;
import com.sut.cpe.repaircom.entity.Sex;
import com.sut.cpe.repaircom.repository.*;
import com.sut.cpe.repaircom.repository.ProvinceRepository;
import com.sut.cpe.repaircom.repository.SexRepository;

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
    ApplicationRunner init(EmployeeRepository employeeRepository, ProvinceRepository provinceRepository,SexRepository sexRepository,AddproductRepository addproductRepository,TypeRepository typeRepository,BrandRepository brandRepository,PartRepository partRepository,BranchRepository branchRepository,FixtypeRepository fixtypeRepository,CustomerRepository customerRepository,PartpiecesnumberforrepairRepository partpiecesnumberforrepairRepository,StatusrepairRepository statusrepairRepository,HeadingRepository headingRepository,CommunicationRepository communicationRepository) {
        return args -> {
            Stream.of("Takoonkan  Bunjan", "Natalee Satong", "Sawadee Yambai").forEach(name -> {
                Employee employee = new Employee(); // สร้าง Object Employee
                employee.setEmpName(name); // set ชื่อ (name) ให้ Object ชื่อ Employee
                employeeRepository.save(employee); // บันทึก Objcet ชื่อ Employee
            });

            Stream.of("ชาย", "หญิง").forEach(name -> {
                Sex sex = new Sex(); // สร้าง Object Sex
                sex.setSex(name); // set ชื่อ (name) ให้ Object ชื่อ Sex
                sexRepository.save(sex); // บันทึก Objcet ชื่อ Sex
            });

            Stream.of("นครราชสีมา", "บุรีรัมย์").forEach(name -> {
                Province province = new Province(); // สร้าง Object Province
                province.setProvince(name); // set ชื่อ (name) ให้ Object ชื่อ Province
                provinceRepository.save(province); // บันทึก Objcet ชื่อ Province
            });

            Stream.of("Cherprang").forEach(name -> {
				Customer customer = new Customer(); // สร้าง Object Customer
				customer.setCusName("aloha baboon"); // set ชื่อ (name) ให้ Object ชื่อ cusName
				customer.setIdentification("1319800215244"); // set ชื่อ (name) ให้ Object ชื่อ idetification
				customer.setBirth(null);// set ชื่อ (name) ให้ Object ชื่อ birth
				customer.setAge(12); // set ชื่อ (name) ให้ Object ชื่อ age
				customer.setAddress("หัวเห็ด"); // set ชื่อ (name) ให้ Object ชื่อ address
				customer.setSubDistrict("บางนา"); // set ชื่อ (name) ให้ Object ชื่อ subDistrict
				customer.setDistrict("บางพลี"); // set ชื่อ (name) ให้ Object ชื่อ district
				customer.setTel("0321254551"); // set ชื่อ (name) ให้ Object ชื่อ tel
				customerRepository.save(customer); // บันทึก Objcet ชื่อ Customer
			});

            Stream.of("Cherprang").forEach(name -> {
				Customer customer = new Customer(); // สร้าง Object Customer
				customer.setCusName("namo tadsa"); // set ชื่อ (name) ให้ Object ชื่อ cusName
				customer.setIdentification("1234567890123"); // set ชื่อ (name) ให้ Object ชื่อ idetification
				customer.setBirth(null);// set ชื่อ (name) ให้ Object ชื่อ birth
				customer.setAge(50); // set ชื่อ (name) ให้ Object ชื่อ age
				customer.setAddress("หัวเห็ด"); // set ชื่อ (name) ให้ Object ชื่อ address
				customer.setSubDistrict("บางนา"); // set ชื่อ (name) ให้ Object ชื่อ subDistrict
				customer.setDistrict("บางพลี"); // set ชื่อ (name) ให้ Object ชื่อ district
				customer.setTel("0212549548"); // set ชื่อ (name) ให้ Object ชื่อ tel
				customerRepository.save(customer); // บันทึก Objcet ชื่อ Customer
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
            partpiecesnumberforrepairRepository.save(new Partpiecesnumberforrepair(1));
            partpiecesnumberforrepairRepository.save(new Partpiecesnumberforrepair(2));
            partpiecesnumberforrepairRepository.save(new Partpiecesnumberforrepair(3));
            partpiecesnumberforrepairRepository.save(new Partpiecesnumberforrepair(4));
            partpiecesnumberforrepairRepository.save(new Partpiecesnumberforrepair(5));

            communicationRepository.save(new Communication("ติดต่อกลับทาง อีเมล"));//ระบบติดต่อสอบถาม
            communicationRepository.save(new Communication("ติดต่อกลับทาง โทรศัพท์"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามข้อมูลเกี่ยวการแจ้งซ่อม"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามข้อมูลอาการและปัญหาคอมพิวเตอร์"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("แจ้งเรื่องร้องเรียน"));//ระบบติดต่อสอบถาม
            headingRepository.save(new Heading("สอบถามเรื่องอื่นๆ"));//ระบบติดต่อสอบถาม

            addproductRepository.save(new Addproduct("RAM","8gb",320));
			addproductRepository.save(new Addproduct("Card","sdfs",840));
			addproductRepository.save(new Addproduct("cpu","64bit",1500));
            addproductRepository.save(new Addproduct("on repair","-",0));
            
            



            addproductRepository.findAll().forEach(System.out::println);
            typeRepository.findAll().forEach(System.out::println);
            brandRepository.findAll().forEach(System.out::println);
            partRepository.findAll().forEach(System.out::println);
            branchRepository.findAll().forEach(System.out::println);
            provinceRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Province บน Terminal
            employeeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
            sexRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Sex บน Termina
            communicationRepository.findAll().forEach(System.out::println);//ระบบติดต่อสอบถาม
            headingRepository.findAll().forEach(System.out::println);//ระบบติดต่อสอบถาม

        };
    }
}
