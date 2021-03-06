package com.sut.cpe.repaircom.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="ADMINREPAIR")

public class Adminrepairindex{
    @Id
    @SequenceGenerator(name="ADMINREPAIR_SEQ",sequenceName="ADMINREPAI_SEQR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ADMINREPAIR_SEQ")
    @Column(name="ADMINREPAIR_ID",unique = false , nullable = true )
    private @NonNull Long id;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[ก-์|A-z|\\s].+")
    private String breakdown;

   

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[R]\\d{7}")
    private String repairwork;

    @NotNull
    @PositiveOrZero
    private Double totalrepairprice;

    @NotNull
    @Column(name="REPAIR_DATE")
    private Date repairDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Statusrepair.class)
    @JoinColumn(name = "STATUSREPAIR_ID", insertable = true)
    private Statusrepair statusrepair;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Type type;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true)
    private Branch branch;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "Customer_ID", insertable = true)
    private Customer customer;
    
    @NotNull
	@ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    private  Brand brand;



    
    public Adminrepairindex(){}
    public Adminrepairindex(Date repairDate,String breakdown,Double totalrepairprice,String repairwork){
        this.repairDate = repairDate;
        this.breakdown = breakdown;
        this.totalrepairprice = totalrepairprice;
        this.repairwork = repairwork;

    }





}