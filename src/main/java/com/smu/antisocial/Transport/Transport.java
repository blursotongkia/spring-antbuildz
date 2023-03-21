package com.smu.antisocial.Transport;

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
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Transport {
    @Id
    @SequenceGenerator( 
        name = "transport_sequence",
        sequenceName = "transport_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transport_sequence"
    )
    private Integer transportid;
    private Integer capacity;
    private String transportName;
}
