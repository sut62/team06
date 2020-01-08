package com.sut.cpe.repaircom.repository;
import com.sut.cpe.repaircom.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartRepository extends JpaRepository<Part, Long> {
    Part findById(long id);
}