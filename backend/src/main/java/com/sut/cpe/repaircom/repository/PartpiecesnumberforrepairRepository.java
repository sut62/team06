package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Partpiecesnumberforrepair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartpiecesnumberforrepairRepository extends JpaRepository<Partpiecesnumberforrepair,Long>{
    Partpiecesnumberforrepair findById(long id);
}