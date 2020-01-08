package com.sut.cpe.repaircom.repository;
import com.sut.cpe.repaircom.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findById(long id);
}