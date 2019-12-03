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
@Table(name="ADDPRODUCT")

public class Addproduct{
    @Id
    @SequenceGenerator(name="ADDPRODUCT_SEQ",sequenceName="ADDPRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ADDPRODUCT_SEQ")
    @Column(name="ADDPRODUCT_ID ",unique = true , nullable = true )
    private @NonNull Long id;
    private String productname;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID",insertable = true)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Part.class)
    @JoinColumn(name = "PART_ID", insertable = true)
    private Part part;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID",insertable = true)
    private Branch branch;

	
}