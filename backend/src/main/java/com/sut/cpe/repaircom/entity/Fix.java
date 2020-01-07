package com.okta.springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="FIX")

public class Fix{
    @Id
    @SequenceGenerator(name="FIX_SEQ",sequenceName="FIX_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="FIX_SEQ")
    @Column(name="FIX_ID ",unique = true , nullable = true )
    private @NonNull Long id;
    private String fixname;
    

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID",insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID",insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Fixtype.class)
    @JoinColumn(name = "FIXTYPE_ID", insertable = true)
    private Fixtype fixtype;

    

	
}