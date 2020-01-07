package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer, Long> {

}