package com.sut.cpe.repaircom.repository;
import com.sut.cpe.repaircom.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findById(long id);
}