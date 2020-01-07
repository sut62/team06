package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Heading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HeadingRepository extends JpaRepository<Heading,Long>{
    Heading findById(long id);
}