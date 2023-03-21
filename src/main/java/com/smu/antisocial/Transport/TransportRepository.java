package com.smu.antisocial.Transport;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {
    
    @Query("SELECT t FROM Transport t WHERE t.capacity >= ?1 ")
    List<Transport> findTransportsByCapacity(Integer capacity);
}
