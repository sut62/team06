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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="PROFILELOGIN")
public class Profilelogin {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private @NonNull Long profileid;

        private
        @NonNull
        @Column(unique = true)
        String username;

        private 
        @NonNull
        @Size(min = 8,message = "รหัสผ่านอย่างน้อย8อักษร") String password;
        
        protected Profilelogin() {}
        public Profilelogin(String username ,String password){
                this.username = username;
                this.password = password;
                }
        
       
}