package com.example.demo.Service;


import com.example.demo.Domain.Request.RouteRequest;
import com.example.demo.Domain.Request.RouteRequestForBD;
import com.example.demo.Domain.Request.StationRequest;
import com.example.demo.Domain.Route;
import com.example.demo.Domain.SearchedRoute;
import com.example.demo.Domain.StationOfARoute;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Repository.StationOfARouteRepository;
import com.example.demo.Service.Interface.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class RouteService implements IRouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationOfARouteRepository stationOfARouteRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteRequest findARouteByTrainId(String train_id) {
        if(!findTrainById(train_id).equals("nu")) {
            Route route = routeRepository.findByIdTrain(train_id);
            List<StationRequest> list = new ArrayList<>();

            for (StationOfARoute station : route.getStationOfARoutes()) {
                list.add(new StationRequest(station.getIdStationRoute(), station.getStationName(),
                        station.getRoute().getId_route(), station.getStationNumber(),
                        station.getArrivalTime()+"", station.getDepartureTime()+""));
            }

            return new RouteRequest(route.getId_route(), route.getId_train(),
                    route.getDate(), route.getDelay(), list);
        }
        return new RouteRequest();
    }

    @Override
    public String findTrainById(String train_id){
        Route route = routeRepository.findByIdTrain(train_id);
        if(route != null)
            return route.getId_route() + "";
        else
            return "nu";
    }

    @Override
    public Route find(String train_id) {
        return routeRepository.findByIdTrain(train_id);
    }

    @Override
    public List<StationRequest> getStationsByRouteId(Long id_route) {
        List<StationRequest> searched_stations = new ArrayList<>();
      List<StationOfARoute> stations = stationOfARouteRepository.findAll();
      for(StationOfARoute station : stations){
                  searched_stations.add(new StationRequest(station.getIdStationRoute(),
                  station.getStationName(), station.getRoute().getId_route(), station.getStationNumber(),
                  station.getArrivalTime()+"", station.getDepartureTime()+""));
      }
      return searched_stations;
    }

    @Override
    public List<StationOfARoute> getStationsByRoute(Long id_route) {
        List<StationOfARoute> searched_stations = new ArrayList<>();
        List<StationOfARoute> stations = stationOfARouteRepository.findAll();
        for(StationOfARoute station : stations){
            searched_stations.add(station);
        }
        return searched_stations;
    }

    @Override
    public List<RouteRequestForBD> getAllRoutes() {
        List<Route> list =  routeRepository.findAll();
        List all_routes = new ArrayList();
        for(Route route : list){
            all_routes.add(new RouteRequestForBD(route.getId_route(), route.getDate() + "",
                    route.getId_train()));
        }

        return all_routes;
    }

    @Override
    public Route findRouteById(Long id) {
        return routeRepository.findByIdRoute(id);
    }

    @Override
    public List<StationRequest> getAllStations() {
        List<StationOfARoute> list = stationOfARouteRepository.findAll();
        List<StationRequest> all_stations =  new ArrayList<>();
        for(StationOfARoute station : list)
            all_stations.add(new StationRequest(station.getIdStationRoute(),
                    station.getStationName(), station.getRoute().getId_route(),
                    station.getStationNumber(), station.getDepartureTime() + "",
                    station.getDepartureTime()+""));
        return all_stations;
    }

    @Override
    public List<SearchedRoute> searchDirectRoutes(String departure, String destination) {
        List<SearchedRoute> searchedRoutes = new ArrayList<>();
        List<Route> route_list = routeRepository.findAll();
        Set<StationOfARoute> stations_list;
        StationOfARoute stationD = null;
        StationOfARoute stationA = null;
        int ct = 0;

        for(Route route : route_list){
            ct = 0;
            stations_list = route.getStationOfARoutes();
            for(StationOfARoute station : stations_list){
                if(station.getStationName().equals(departure)) {
                    stationD = station;
                    ct++;
                }
                if(station.getStationName().equals(destination)){
                    stationA = station;
                    ct++;
                }
            }
            if(ct == 2 && stationA.getDepartureTime().toString().substring(11,16).
                    compareTo(stationD.getArrivalTime().toString().substring(11,16)) > 0)
                searchedRoutes.add(new SearchedRoute(route.getId_route(), stationD.getStationName(),
                        stationA.getStationName(), stationD.getDepartureTime() + "",
                        stationA.getArrivalTime() + "", route.getId_train()));
        }

        return searchedRoutes;
    }
}
