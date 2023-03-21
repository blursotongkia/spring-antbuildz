package com.smu.antisocial.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    
    @Query("SELECT password FROM Admin a WHERE a.username = ?1")
    String getPassword(String username);

    @Query("SELECT a FROM Admin a WHERE a.username = ?1")
    Optional<Admin> findAdminbyUsername(String username);

}
