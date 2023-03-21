package com.smu.antisocial.Request;

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
@Table(name="request")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Request {

    @Id
    @SequenceGenerator( 
        name = "request_sequence",
        sequenceName = "request_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "request_sequence"
    )
    private Integer requestid;
    private String startDateTime;
    private String endDateTime;
    private String specialRequest;
    private String pricingType;
    private Integer quantity;
    private String startLocation;
    private String endLocation;
    private Integer userid;
    private Integer equipmentID;
    private boolean active = true;
}
