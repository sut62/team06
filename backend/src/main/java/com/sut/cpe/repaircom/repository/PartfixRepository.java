package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Partfix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartfixRepository extends JpaRepository<Partfix,Long>{
    Partfix findById(long id);
}