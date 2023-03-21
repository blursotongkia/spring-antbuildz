package com.smu.antisocial.Rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r WHERE r.requestid = ?1 ")
    Rental getRentalByRequestId(Integer requestid);
}
