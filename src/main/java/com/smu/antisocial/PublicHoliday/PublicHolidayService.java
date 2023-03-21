package com.smu.antisocial.PublicHoliday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublicHolidayService {
    @Autowired
    private final PublicHolidayRepository publicholidayRepository;

    public List<PublicHoliday> getPublicHolidays() {
        return publicholidayRepository.findAll();
    }

    public PublicHoliday getPublicHolidayByDate(LocalDate date) {
        return publicholidayRepository.findPublicHolidaybyDate(date).orElseThrow(
            () -> new IllegalStateException("Date is not Public Holiday"));
    }

    public List<String> getPublicHolidayDates() {
        List<PublicHoliday> publicholidays = this.getPublicHolidays();
        List<String> phList = new ArrayList<>();
        for (PublicHoliday publicholiday : publicholidays) {
            phList.add(publicholiday.getDate().toString());
        }
        return phList;
    } 

}
