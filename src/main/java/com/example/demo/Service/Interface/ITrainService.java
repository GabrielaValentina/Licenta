package com.example.demo.Service.Interface;

import com.example.demo.Domain.Route;
import com.example.demo.Domain.StationOfARoute;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public interface ITrainService {

    List<StationOfARoute> getAllStationsOfATrain(String train_id);
    Route findARouteByTrain(String train_id);
    Route findARouteByDateAndDepartureAndDestination(Timestamp date, String departure, String destination);
    List<Route> findAllRoutesIdByDateAndDepartureAndDestination(Timestamp date, String departure, String destination);
    HashMap<Long, List<StationOfARoute>> findAllRoutesByDateAndDepartureAndDestination(Timestamp date, String departure, String destination);

}
