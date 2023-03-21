package com.smu.antisocial.PublicHoliday;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer> {

    @Query("SELECT ph FROM PublicHoliday ph WHERE ph.date = ?1")
    Optional<PublicHoliday> findPublicHolidaybyDate(LocalDate date);
}