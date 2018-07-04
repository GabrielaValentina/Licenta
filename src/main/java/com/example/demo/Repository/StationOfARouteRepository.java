package com.example.demo.Repository;

import com.example.demo.Domain.StationOfARoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationOfARouteRepository extends JpaRepository<StationOfARoute, Long>{

    @Query(value = "SELECT DISTINCT * FROM station_of_a_route WHERE route_id =1 ", nativeQuery = true)
    List<StationOfARoute> getAllStationsOfATrain (Long id_route);

    @Query(value = "SELECT * from station_of_a_route WHERE route_id =:route", nativeQuery = true)
    List<StationOfARoute> getAllStationsByRouteId(@Param("route") Long route);

    @Query(value = "SELECT * from station_of_a_route ORDER BY station_number ASC", nativeQuery = true)
    List<StationOfARoute> findAll();

}
