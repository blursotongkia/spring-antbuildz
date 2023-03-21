package com.smu.antisocial.Partner;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    @Query("SELECT p FROM Partner p WHERE p.userid = ?1")
    Optional<Partner> findPartnerbyUserId(Integer userid);
}