package com.sut.cpe.repaircom.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="COMMUNICATION")
public class Communication {
 @Id
 @SequenceGenerator(name="COMMUNICATION_SEQ",sequenceName="COMMUNICATION_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMMUNICATION_SEQ")
 @Column(name="COMMUNICATION_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NotNull String  communicationName;

 @OneToMany(fetch = FetchType.EAGER)
 private Collection<Contact> contact;


 
    public Communication(){}
    public Communication(String communicationName) {
        this.communicationName = communicationName;
        }
}