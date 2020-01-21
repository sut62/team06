package com.sut.cpe.repaircom.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="ADDPRODUCT")

public class Addproduct{
    @Id
    @SequenceGenerator(name="ADDPRODUCT_SEQ",sequenceName="ADDPRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ADDPRODUCT_SEQ")
    @Column(name="ADDPRODUCT_ID ",unique = true , nullable = true )
    private @NonNull Long id;
    
    @NotNull 
    private String productname;
    private @NonNull String description;
    private @NonNull int price;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID",insertable = true)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID",insertable = true)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Part.class)
    @JoinColumn(name = "PART_ID", insertable = true)
    private Part part;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID",insertable = true)
    private Branch branch;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Adminrepairindex> adminrepairindexs;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Receipt> receipts;

    public Addproduct(){}
    public Addproduct(String productname,String  description,int price){
        this.productname = productname;
        this.description = description;
        this.price = price;
       
        }


	


    
}