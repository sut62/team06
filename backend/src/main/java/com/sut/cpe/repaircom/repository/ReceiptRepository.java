package com.sut.cpe.repaircom.repository;

import com.sut.cpe.repaircom.entity.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReceiptRepository extends JpaRepository<Receipt,Long>{
    Receipt findById(long id);
}