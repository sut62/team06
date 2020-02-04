package com.sut.cpe.repaircom.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotNull;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Sex")
public class Sex {

    @Id
    @SequenceGenerator(name="SEX_SEQ",sequenceName="SEX_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEX_SEQ")
    @Column(name="SEX_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NotNull String sex;
   

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "customerSex"
    private Collection<Customer> data;
    
}
