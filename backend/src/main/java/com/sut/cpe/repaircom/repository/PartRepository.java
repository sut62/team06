package com.okta.springbootvue.repository;
import com.okta.springbootvue.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartRepository extends JpaRepository<Part, Long> {
    Part findById(long id);
}