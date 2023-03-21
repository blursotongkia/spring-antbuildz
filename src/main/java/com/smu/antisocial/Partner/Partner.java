package com.smu.antisocial.Partner;

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
@Table(name="partner")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Partner {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "partner_sequence"
    )
    @SequenceGenerator( 
        name = "partner_sequence",
        sequenceName = "partner_sequence",
        allocationSize = 1
    )
    @Column(name = "partnerid",updatable = false)
    private Integer partnerid;
    @Column(unique = true)
    private Integer userid;
    
}

