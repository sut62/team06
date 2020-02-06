package com.sut.cpe.repaircom.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.*;
@Data
@Entity
@Getter
@Setter
@ToString
//@NoArgsConstructor
@EqualsAndHashCode

@Table(
    name="FIX" , 
    uniqueConstraints = @UniqueConstraint(columnNames = {"QUEUE"})
    )

public class Fix{
    @Id
    @SequenceGenerator(name="FIX_SEQ",sequenceName="FIX_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="FIX_SEQ")
    @Column(name="FIX_ID ",unique = true , nullable = true )
    private Long id;  
    
    @NotNull
	@Size(max = 200,min = 1)
    private @NonNull String fixname;
   
    @NotNull
    @Column(name = "QUEUE")
	@Pattern(regexp = "\\d{8}")
    private String queue;
        
    

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID",insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID",insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Fixtype.class)
    @JoinColumn(name = "FIXTYPE_ID", insertable = true)
    private Fixtype fixtype;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Machinecolor.class)
    @JoinColumn(name = "MACCHINECOLOR_ID", insertable = true)
    private Machinecolor Machinecolor;

    public Fix(){}
    public Fix(String fixname,String queue){
        this.fixname =fixname;
        this.queue =queue;
    }
	public void setFixname(String fixname) {
        this.fixname = fixname;
    }
    public String getFixname() {
        return fixname;
   }

	public void setQueue(String queue) {
        this.queue = queue;
	}
    
    public String getQueue() {
        return queue;
   }
	

	
}