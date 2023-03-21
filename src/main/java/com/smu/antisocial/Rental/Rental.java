package com.smu.antisocial.Rental;

import java.time.LocalDateTime;

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
@Table(name="rental")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Rental {
    @Id
    @SequenceGenerator( 
        name = "rental_sequence",
        sequenceName = "rental_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rental_sequence"
    )
    private Integer rentalid;
    private Integer requestid;
    private String listingIDlist;
    private LocalDateTime creationDate;
    private String status = "Awaiting Payment";
    private String userStatus = "Not Completed";
    private String partnerStatus = "Not Completed";
}
