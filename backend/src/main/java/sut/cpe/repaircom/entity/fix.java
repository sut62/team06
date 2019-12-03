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
@Table(name="FIX")
public class Fix{
 @Id
 @SequenceGenerator(name="FIX_SEQ",sequenceName="FIX_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIX_SEQ")
 @Column(name="FIX_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NonNull String fix;
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<logcomrepair> logcomrepair;
    protected Fix(){}
    public Fix(String fix){
        this.fix = fix;
    }
}