package com.okta.springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixApplication.class, args);
	}
	@Bean
	//("/addproduct/{productname}/{description}/{brand_id}/{part_id}/{branch_id}")
	ApplicationRunner init(FixRepository fixRepository,EmployeeRepository employeeRepository,CustomerRepository customerRepository,FixtypeRepository fixtypeRepository){
		return args ->{
			employeeRepository.save(new Employee("woramet meksuwan"));
			employeeRepository.save(new Employee("suradet tongthai"));

			customerRepository.save(new Customer("jo"));
			customerRepository.save(new Customer("dom"));

			fixtypeRepository.save(new Fixtype("Computer"));
			fixtypeRepository.save(new Fixtype("Notebook"));
			
			fixRepository.findAll().forEach(System.out::println);
			employeeRepository.findAll().forEach(System.out::println);
			customerRepository.findAll().forEach(System.out::println);
			fixtypeRepository.findAll().forEach(System.out::println);
            
		};
	}
}
