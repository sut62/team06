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
    private @NonNull Date repairdate;
    private @NonNull String breakdown;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Statusrepair.class)
    @JoinColumn(name = "STATUSREPAIR_ID", insertable = true)
    private @NonNull Statusrepair statusrepair;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Typemachrepair.class)
    @JoinColumn(name = "TYPEMACHREPAIR_ID", insertable = true)
    private @NonNull Typemachrepair typemachrepair;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true)
    private @NonNull Branch branch;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private @NonNull Employee employee;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = fix.class)
    @JoinColumn(name = "FIX_ID", insertable = true)
    private @NonNull Fix fix;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = addproduct.class)
    @JoinColumn(name = "ADDPRODUCT_ID", insertable = true)
    private @NonNull Addproduct addproduct;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND", insertable = true)
    private @NonNull Brand brand;

    protected Adminrepairindex(){}
    public Adminrepairindex(Date repairdate ){
        this.repairdate = repairdate;
        }
    public Adminrepairindex(String breakdown){
        this.breakdown = breakdown;
    }
}