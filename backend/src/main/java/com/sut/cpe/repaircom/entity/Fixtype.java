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
@Table(name="FIXTYPE")
public class Fixtype {

    @Id
    @SequenceGenerator(name="FIXTYPE_SEQ",sequenceName="FIXTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIXTYPE_SEQ")
    @Column(name="FIXTYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String fixtypename;

    @OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Fix> data;

	
    protected Fixtype(){}
    public Fixtype(String fixtypename ){
        this.fixtypename = fixtypename;
        }	
}
