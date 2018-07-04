package com.example.demo.Service.Interface;

import com.example.demo.Domain.Request.RouteRequest;
import com.example.demo.Domain.Request.RouteRequestForBD;
import com.example.demo.Domain.Request.StationRequest;
import com.example.demo.Domain.Route;
import com.example.demo.Domain.SearchedRoute;
import com.example.demo.Domain.StationOfARoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IRouteService {

    RouteRequest findARouteByTrainId(String train_id);

    Route find(String train_id);

    String findTrainById(String train_id);

    List<StationRequest> getStationsByRouteId(Long is_route);

    List<RouteRequestForBD> getAllRoutes();

    Route findRouteById(Long id);

    List<StationRequest> getAllStations();

    List<StationOfARoute> getStationsByRoute(Long id_route);

    List<SearchedRoute> searchDirectRoutes(String departure, String destination);
}
