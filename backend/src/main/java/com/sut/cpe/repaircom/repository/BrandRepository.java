package com.okta.springbootvue.repository;
import com.okta.springbootvue.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findById(long id);
}