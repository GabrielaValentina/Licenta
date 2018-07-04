package com.example.demo.Repository;

import com.example.demo.Domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByIdTrain(String id_train);
    Route findByDate(Timestamp date);
    Route findByIdRoute(Long id);
}
