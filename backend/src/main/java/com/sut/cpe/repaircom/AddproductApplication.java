package com.okta.springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddproductApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddproductApplication.class, args);
	}
	@Bean
	//("/addproduct/{productname}/{description}/{brand_id}/{part_id}/{branch_id}")
	ApplicationRunner init(AddproductRepository addproductRepository,TypeRepository typeRepository,BrandRepository brandRepository,PartRepository partRepository,BranchRepository branchRepository){
		return args ->{
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
			
			
			branchRepository.save(new Branch("สาขาจังหวัดกรุงเทพมหานคร"));
			branchRepository.save(new Branch("สาขาจังหวัดปราจีนบุรี"));
			branchRepository.save(new Branch("สาขาจังหวัดนครราชสีมา"));
			branchRepository.save(new Branch("สาขาจังหวัดปัตตานี"));
			branchRepository.save(new Branch("สาขาจังหวัดยะลา"));
			
			
			addproductRepository.findAll().forEach(System.out::println);
			typeRepository.findAll().forEach(System.out::println);
			brandRepository.findAll().forEach(System.out::println);
			partRepository.findAll().forEach(System.out::println);
            branchRepository.findAll().forEach(System.out::println);
			
		};
	}
}
