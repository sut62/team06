package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Statusrepair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatusrepairRepository extends JpaRepository<Statusrepair,Long>{
    Statusrepair findById(long id);
}