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
@Table(name="PARTPIECESNUMBERFORREPAIR")
public class Partpiecesnumberforrepair {
 @Id
 @SequenceGenerator(name="PARTPIECESNUMBERFORREPAIR_SEQ",sequenceName="PARTPIECESNUMBERFORREPAIR_SEQ")
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PARTPIECESNUMBERFORREPAIR_SEQ")
 @Column(name="PARTPIECESNUMBERFORREPAIR_ID",unique = true, nullable = true)
 private @NonNull Long id;
 private @NonNull int piece_number;
 @OneToMany(fetch = FetchType.EAGER)
 private Collection<Adminrepairindex> adminrepairindexs;
    protected Partpiecesnumberforrepair(){}
    public Partpiecesnumberforrepair(int piece_number){
        this.piece_number = piece_number;
        }
}