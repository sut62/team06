package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Addproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddproductRepository extends JpaRepository<Addproduct,Long>{
}