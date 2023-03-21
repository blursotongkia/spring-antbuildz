package com.smu.antisocial.Bid;

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
@Table(name="bid")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Bid {

    @Id
    @SequenceGenerator( 
        name = "bid_sequence",
        sequenceName = "bid_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "bid_sequence"
    )
    private Integer bidID;
    private Integer requestID;
    private String listingIDList;
    private double perTripPrice;
    private double perHrPrice;
    private double fullDayPrice;
    private double rateAfter5Price;
    private double rateAfter10Price;
    private double sundayPHPrice;
    private double totalPrice;
    private String status = "ongoing";
}
