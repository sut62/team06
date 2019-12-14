package com.sut.cpe.repaircom.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.sut.cpe.healthInsurance.entity.Employee;
import com.sut.cpe.healthInsurance.entity.Province;
import com.sut.cpe.healthInsurance.entity.Sex;

import java.util.Date;
import java.util.Collection;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@Column(name="CUSTOMER_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String cusName;
	private @NonNull String identification;
	private @NonNull int age;
	private @NonNull Date birth;
	private @NonNull String address;
	private @NonNull String subDistrict;
	private @NonNull String district;
	private @NonNull String tel;

}