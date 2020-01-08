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
@Table(name="TYPE")
public class Type {

    @Id
    @SequenceGenerator(name="TYPE_SEQ",sequenceName="TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TYPE_SEQ")
    @Column(name="TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String typename;

    @OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Addproduct> data;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Adminrepairindex> adminrepairindexs;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Receipt> receipts;
	
    protected Type(){}
    public Type(String typename ){
        this.typename = typename;
        }	
}
