package com.smu.antisocial.PublicHoliday;

import java.time.LocalDate;

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
@Table(name="publicholiday")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PublicHoliday {
    @Id
    @SequenceGenerator( 
        name = "public_holiday_sequence",
        sequenceName = "public_holiday_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "public_holiday_sequence"
    )
    private Integer dateid;
    private LocalDate date;
    private String description;
}
