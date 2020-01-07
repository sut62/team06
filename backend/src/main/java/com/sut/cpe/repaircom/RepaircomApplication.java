package com.sut.cpe.repaircom;

import com.sut.cpe.repaircom.entity.Employee;
import com.sut.cpe.repaircom.entity.Province;
import com.sut.cpe.repaircom.entity.Sex;
import com.sut.cpe.repaircom.repository.EmployeeRepository;
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
	ApplicationRunner init(EmployeeRepository employeeRepository, ProvinceRepository provinceRepository,SexRepository sexRepository) {
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

			provinceRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Province บน Terminal
			employeeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
			sexRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Sex บน Termina
		};
	}
}
