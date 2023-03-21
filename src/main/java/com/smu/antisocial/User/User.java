package com.smu.antisocial.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {

    @Id
    @SequenceGenerator( 
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    @Column(name = "userid", updatable = false)
    private Integer userid;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    @Column(unique = true)
    private String email;
    private String companyName;
    private String phoneNumber;
    private String address;
    private boolean activated;
    private String token;
    private boolean partnerRequest;
}
