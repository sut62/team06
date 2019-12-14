package com.sut.cpe.repaircom.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name="REPIARTYPE")
public class Repairtype {
 @Id
 @SequenceGenerator(name="REPIARTYPE_SEQ",sequenceName="REPIARTYPE_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="REPIARTYPE_SEQ")
 @Column(name="PAYCHECKS_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NonNull String caserepair;
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<logcomrepair> logcomrepair;
    protected Repairtype(){}
    public Repairtype(String caserepair{
        this.caserepair = caserepair;
    }
}