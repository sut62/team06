package com.okta.springbootvue.repository;
import com.okta.springbootvue.entity.Fixtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FixtypeRepository extends JpaRepository<Fixtype, Long> {
    Fixtype findById(long id);
}