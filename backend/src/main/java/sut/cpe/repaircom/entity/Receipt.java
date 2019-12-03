package sut.cpe.repaircom.entity;
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

    @Column(name="RECEIPT_DATE")
    private @NonNull Date receiptDate;

  
    protected Receipt(){}
        public Receipt(Date receiptDate ){
            this.receiptDate = receiptDate;
            }
//test
}