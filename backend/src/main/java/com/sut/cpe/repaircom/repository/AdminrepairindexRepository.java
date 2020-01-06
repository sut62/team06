package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Adminrepairindex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminrepairindexRepository extends JpaRepository<Adminrepairindex,Long>{
    
}