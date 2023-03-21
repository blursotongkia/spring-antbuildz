package com.smu.antisocial.Request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<Request, Integer>{
    
    @Query("SELECT r from Request r where r.userid = ?1")
    List<Request> getRequestByUserId(Integer userid);
}
