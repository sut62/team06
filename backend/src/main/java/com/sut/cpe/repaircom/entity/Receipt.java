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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="RECEIPT"
    ,uniqueConstraints = @UniqueConstraint(columnNames = {"QUEUE"}))

public class Receipt{
    @Id
    @SequenceGenerator(name="RECEIPT_SEQ",sequenceName="RECEIPT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="RECEIPT_SEQ")
    @Column(name="RECEIPT_ID ",unique = true , nullable = true )
    private @NonNull Long id;
    private @NonNull String receiptCusname;

    @NotNull
    @Column(name = "QUEUE")
	@Pattern(regexp = "[T][C][G]\\d{7}")
	private String receiptNum;

    @NotNull
	@Size(max = 13,min = 13)
    private String receiptCusident; 

    @NotNull
	@Pattern(regexp = "\\d{10}")
	private String receiptCustel;


  

    @Column(name="RECEIPT_DATE")
    private @NonNull Date receiptDate;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true)
    private @NonNull Branch branch;


    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID",insertable = true)
    private @NonNull Employee employee;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Type.class)
    @JoinColumn(name ="TYPE_ID",insertable = true)
    private @NonNull Type type;

    
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Adminrepairindex.class)
    @JoinColumn(name ="ADMINREPAIRINDEX_ID",insertable = true)
    private @NonNull Adminrepairindex adminrepairindex;



        public Receipt(){}
        public Receipt(String receiptCusident,String receiptCusname,String receiptCustel,Date receiptDate,String receiptNum){
            this.receiptCusname = receiptCusname;
            this.receiptCusident = receiptCusident;
            this.receiptCustel = receiptCustel;
            this.receiptDate = receiptDate;
            this.receiptNum = receiptNum;
           

            }

}