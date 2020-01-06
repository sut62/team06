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

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="PROVINCE")
public class Province {

    @Id
    @SequenceGenerator(name="PROVINCE_SEQ",sequenceName="PROVINCE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROVINCE_SEQ")
    @Column(name="PROVINCE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String province;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "customerProvince"
    private Collection<Customer> data;

}
