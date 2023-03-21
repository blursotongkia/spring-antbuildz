package com.smu.antisocial.PublicHoliday;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/publicholiday")
@AllArgsConstructor
public class PublicHolidayController {
    @Autowired
    private final PublicHolidayService publicholidayService;

    @GetMapping
	public List<PublicHoliday> getPublicHolidays(){
        return publicholidayService.getPublicHolidays();
	}

    @GetMapping(path="{date}")
    public PublicHoliday getPublicHoliday(@PathVariable("date") String date) throws ParseException{
        LocalDate date1 = LocalDate.parse(date);
        return publicholidayService.getPublicHolidayByDate(date1);
    }
}
