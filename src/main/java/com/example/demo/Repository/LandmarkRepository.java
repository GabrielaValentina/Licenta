package com.example.demo.Repository;

import com.example.demo.Domain.Landmark;
import com.example.demo.Domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Integer> {
    List<Landmark> findAll();

    List<Landmark> findByRoute(Long route);
}
