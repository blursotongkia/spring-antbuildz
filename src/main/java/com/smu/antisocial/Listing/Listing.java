package com.smu.antisocial.Listing;

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
@Table(name="listing")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Listing {
    @Id
    @SequenceGenerator( 
        name = "listing_sequence",
        sequenceName = "listing_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "listing_sequence"
    )
    private Integer listingid;
    private Integer equipmentid;
    private Integer partnerid;
}
