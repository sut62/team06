package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Fix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FixRepository extends JpaRepository<Fix,Long>{
}