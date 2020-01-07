package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Communication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommunicationRepository extends JpaRepository<Communication,Long>{
    Communication findById(long id);
}