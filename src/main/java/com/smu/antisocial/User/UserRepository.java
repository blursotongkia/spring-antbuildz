package com.smu.antisocial.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserbyEmail(String email);

    @Query("SELECT password FROM User u WHERE u.username = ?1")
    String getPassword(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserbyUsername(String username);

}