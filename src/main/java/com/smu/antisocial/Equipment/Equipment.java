package com.smu.antisocial.Equipment;

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
@Table(name="equipment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Equipment {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "equipment_sequence"
    )
    @SequenceGenerator(
        name = "equipment_sequence",
        sequenceName = "equipment_sequence",
        allocationSize = 1
    )
    private Integer equipmentID;
    private String equipmentName;
    private double height;
    private double width;
    private double length;
    private Integer transportID;
    private String description;
    private String url;
    private double perTripPrice;
    private double perHrPrice;
    private double fullDayPrice;
    private double rateAfter5Price;
    private double rateAfter10Price;
    private double sundayPHPrice;
}
