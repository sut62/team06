package com.sut.cpe.repaircom.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="MACHINECOLOR")
public class Machinecolor {

    @Id
    @SequenceGenerator(name="MACHINECOLOR_SEQ",sequenceName="MACHINECOLOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MACHINECOLOR_SEQ")
    @Column(name="MACHINECOLOR_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NotNull String machinecolor;

    @OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Fix> data;

	
    public Machinecolor(){}
    public Machinecolor(String machinecolor ){
        this.machinecolor = machinecolor;
        }	
}
