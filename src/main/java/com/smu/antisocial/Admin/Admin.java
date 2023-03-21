package com.smu.antisocial.Admin;

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
@Table(name="admin")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Admin {
    @Id
    @SequenceGenerator( 
        name = "admin_sequence",
        sequenceName = "admin_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "admin_sequence"
    )
    private Integer adminid;
    private String username;
    private String password;
}
