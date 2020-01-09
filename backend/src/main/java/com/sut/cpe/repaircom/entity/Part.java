package com.sut.cpe.repaircom.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
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
@EqualsAndHashCode
@Table(name="PART")
public class Part {

    @Id
    @SequenceGenerator(name="PART_SEQ",sequenceName="PART_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_SEQ")
    @Column(name="PART_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String partname;
    private @NonNull int partprice;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Addproduct> data;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Adminrepairindex> adminrepairindexs;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Receipt> receipts;


	
    protected Part(){}
    public Part(String partname ){
        this.partname = partname;
        }
        public Part(int partprice ){
        this.partprice = partprice;
        }
        	
}
