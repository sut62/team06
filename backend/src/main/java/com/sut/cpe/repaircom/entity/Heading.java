package com.sut.cpe.repaircom.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name="HEADING")
public class Heading {
 @Id
 @SequenceGenerator(name="HEADING_SEQ",sequenceName="HEADING_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HEADING_SEQ")
 @Column(name="HEADING_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NotNull String headingName;
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<Contact> contact;


    public Heading(){}
    public Heading(String headingName ){
        this.headingName = headingName;
        }
}