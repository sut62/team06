package com.sut.cpe.repaircom.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="PARTFIX")
public class Partfix {

    @Id
    @SequenceGenerator(name="PARTFIX_SEQ",sequenceName="PARTFIX_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PARTFIX_SEQ")
    @Column(name="PARTFIX_ID",unique = true, nullable = true)
    private @NonNull Long partfixId;
    private @NonNull String useforrepair;

    @JsonIgnoreProperties("partfix")
    @ManyToOne(targetEntity = Adminrepairindex.class)
    @JoinColumn(name="ADMINREPAIR_ID",nullable = false)
    private Adminrepairindex adminrepairindex;

    public Partfix(String useforrepair){
        this.useforrepair = useforrepair;
    }

}
