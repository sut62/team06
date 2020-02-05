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
import javax.validation.constraints.Pattern;


@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="STATUSREPAIR")
public class Statusrepair {
 @Id
 @SequenceGenerator(name="STATUSREPAIR_SEQ",sequenceName="STATUSREPAIR_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATUSREPAIR_SEQ")
 @Column(name="STATUSREPAIR_ID",unique = true, nullable = true)
 private @NonNull Long id;

 @NotNull
 @Pattern(regexp = "รับเรื่องแล้ว|กำลังดำเนินการ|รออะไหล่|ดำเนินการเสร็จสิ้น")
 private String currentstatus;

 
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<Adminrepairindex> adminrepairindexs;
    public Statusrepair(){}
    public Statusrepair(String currentstatus ){
        this.currentstatus = currentstatus;
        }
}