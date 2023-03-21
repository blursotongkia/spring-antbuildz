package com.smu.antisocial.Bid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    
    @Query("SELECT b from Bid b WHERE status='success'")
    List<Bid> getSuccessfulBids();

    List<Bid> findAllByRequestID(Integer requestID);

    List<Bid> findTop3ByRequestIDOrderByTotalPriceAsc(Integer requestID);
}