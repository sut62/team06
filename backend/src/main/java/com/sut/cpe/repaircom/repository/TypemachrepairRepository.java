package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Typemachrepair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TypemachrepairRepository extends JpaRepository<Typemachrepair,Long>{
    Typemachrepair findById(long id);
}