package com.sut.cpe.repaircom.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
//import java.util.Date;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "CONTACT")

public class Contact {
    @Id
    @SequenceGenerator(name = "CONTACT_SEQ", sequenceName = "CONTACT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    @Column(name = "CONTACT_ID ", unique = true, nullable = true)
    private Long id;
    private @NonNull String detail;
    private @NonNull String email;
    private @NonNull String phone;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "BRANCH_ID", insertable = true, nullable = true)
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Contact.class)
    @JoinColumn(name = "COMMUNICATION_ID", insertable = true, nullable = true)
    private Communication communication;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true, nullable = true)
    private Customer createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "HEADING_ID", insertable = true, nullable = true)
    private Heading heading;

    
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact(String phone){
        this.phone = phone;
    } 
	
}
