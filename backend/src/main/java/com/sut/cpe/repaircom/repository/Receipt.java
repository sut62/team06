package com.sut.cpe.repaircom.entity;
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
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="RECEIPT")

public class Receipt{
    @Id
    @SequenceGenerator(name="RECEIPT_SEQ",sequenceName="RECEIPT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="RECEIPT_SEQ")
    @Column(name="RECEIPT_ID ",unique = true , nullable = true )
    private @NonNull Long id;
    private @NonNull String receiptPrice;

    @Column(name="RECEIPT_DATE")
    private @NonNull Date receiptDate;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true)
    private @NonNull Branch branch;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMERE_ID",insertable = true)
    private @NonNull Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID",insertable = true)
    private @NonNull Employee employee;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Type.class)
    @JoinColumn(name ="TYPE_ID",insertable = true)
    private @NonNull Type type;

    protected Receipt(){}
        public Receipt(Date receiptDate,String receiptPrice ){
            this.receiptDate = receiptDate;
            this.receiptPrice = receiptPrice;
            }

}