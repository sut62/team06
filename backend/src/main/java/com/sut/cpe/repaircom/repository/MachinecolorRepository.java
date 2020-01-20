package com.sut.cpe.repaircom.repository;
import com.sut.cpe.repaircom.entity.Machinecolor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MachinecolorRepository extends JpaRepository<Machinecolor, Long> {
    Machinecolor findById(long id);
}