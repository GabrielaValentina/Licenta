package com.example.demo.Repository;

import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmailAddressAndPassword(String email_address, String password);



    @Query(value = "SELECT * FROM USER", nativeQuery = true)
    List<User> getAll();

    User findByEmailAddress(String email_address);
}
