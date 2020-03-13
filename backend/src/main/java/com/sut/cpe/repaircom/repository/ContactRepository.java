package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactRepository extends JpaRepository<Contact,Long>{
    Contact findById(long id);
    Contact findByContactcode(String contactcode);
}