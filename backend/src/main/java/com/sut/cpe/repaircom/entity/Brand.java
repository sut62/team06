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
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="BRAND")
public class Brand {
 @Id
 @SequenceGenerator(name="BRAND_SEQ",sequenceName="BRAND_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BRAND_SEQ")
 @Column(name="BRAND_ID",unique = true, nullable = true)
 private @NonNull Long id;
 @NotNull
 private String brandname;

 @OneToMany(fetch = FetchType.EAGER)
    
 private Collection<Addproduct> data;

 public Brand(){}
 public Brand(String brandname ){
     this.brandname = brandname;
     }
}