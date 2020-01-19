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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sut.cpe.repaircom.entity.*;

import java.util.Collection;
import java.util.Date;



@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@Column(name="CUSTOMER_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String cusName;

	@NotNull
	@Size(max = 13,min = 13)
	private String identification;

	private @NonNull int age;
	private @NonNull Date birth;
	private @NonNull String address;
	private @NonNull String subDistrict;
	private @NonNull String district;

	@NotNull
	@Pattern(regexp = "\\d{10}")
	private String tel;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sex.class)
    @JoinColumn(name = "SEX_ID", insertable = true)
    private Sex customerSex;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
	private Province customerProvince;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Collection<Adminrepairindex> adminrepairindexs;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Contact> contact;
    @OneToMany(fetch = FetchType.EAGER)
	private Collection<Receipt> receipts;
	@OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Fix> fixs;
	
	public void setCusName(String cusName) {
        this.cusName = cusName;
    }

	public String getCusName() {
        return cusName;
	}
	
	public void setIdentification(String identification) {
        this.identification = identification;
    }

	public String getIdentification() {
        return identification;
	}
	
	public void setAge(int age) {
        this.age = age;
    }

	public int getAge() {
        return age;
	}
	
	public void setBirth(Date birth) {
        this.birth = birth;
    }

	public Date getBirth() {
        return birth;
	}
	
	public void setAddress(String address) {
        this.address = address;
    }

	public String getAddress() {
        return address;
	}
	
	public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

	public String getSubDistrict4() {
        return subDistrict;
	}

	public void setDistrict(String district) {
        this.district = district;
    }

	public String getDistrict() {
        return district;
	}
	
	public void setTel(String tel) {
        this.tel = tel;
    }

	public String getTel() {
        return tel;
	}
	
}

