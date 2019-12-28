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
@Table(name="TYPEMACHREPAIR")
public class Typemachrepair {
 @Id
 @SequenceGenerator(name="STATUSREPAIR_SEQ",sequenceName="STATUSREPAIR_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATUSREPAIR_SEQ")
 @Column(name="STATUSREPAIR_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NonNull String typerp;
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<Adminrepairindex> adminrepairindexs;
    protected Typemachrepair(){}
    public Typemachrepair(String typerp){
        this.typerp = typerp;
        }
}