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

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="BRANCH")
public class Branch {

    @Id
    @SequenceGenerator(name="BRANCH_SEQ",sequenceName="BRANCH_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BRANCH_SEQ")
    @Column(name="BRANCH_ID",unique = true, nullable = true)
    private @NonNull Long id;
    @NotNull
    private String branchname;

    @OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Addproduct> data;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Adminrepairindex> adminrepairindexs;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Contact> contact;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Receipt> receipts;

	public Branch(){}
    public Branch(String branchname){
        this.branchname = branchname;
        }
}
