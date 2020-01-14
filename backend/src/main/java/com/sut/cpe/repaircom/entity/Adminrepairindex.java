package com.sut.cpe.repaircom.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Column(name="ADMINREPAIR_ID",unique = true , nullable = true )
    private @NonNull Long id;

    @NotNull
    @Size(min = 3,max = 50)
    @Column(unique = true)
    private String breakdown;

    @NotNull
    private String partn2;

    @NotNull
    private String partn3;
    @NotNull
    private String partn4;
    @NotNull
    private @NonNull String partn5;


    @Column(name="REPAIR_DATE",nullable = true)
    private Date repairDate;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Statusrepair.class)
    @JoinColumn(name = "STATUSREPAIR_ID", insertable = true)
    private Statusrepair statusrepair;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true)
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "Customer_ID", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Addproduct.class)
    @JoinColumn(name = "PART_ID", insertable = true)
    private  Addproduct addproduct;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    private  Brand brand;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Partpiecesnumberforrepair.class)
    @JoinColumn(name = "PARTPIECESNUMBERFORREPAIR_ID", insertable = true)
    private  Partpiecesnumberforrepair partpiecesnumberforrepair;
    
    public Adminrepairindex(){}
    public Adminrepairindex(Date repairDate,String breakdown,String partn2,String partn3,String partn4,String partn5){
        this.repairDate = repairDate;
        this.breakdown = breakdown;
        this.partn2 = partn2;
        this.partn3 = partn3;
        this.partn4 = partn4;
        this.partn5 = partn5;
    }





}