package com.example.demo.Controller;

import com.example.demo.Domain.Request.RouteRequest;
import com.example.demo.Domain.Request.RouteRequestForBD;
import com.example.demo.Domain.Request.StationRequest;
import com.example.demo.Domain.Route;
import com.example.demo.Domain.SearchedRoute;
import com.example.demo.Service.Interface.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    IRouteService serviceRoute;

    @RequestMapping (value = "/getStations/{route_id}",  method = RequestMethod.GET)
    public List<StationRequest> getStationsByRouteId(@PathVariable("route_id") Long route_id){
        List<StationRequest> searched_stations  = serviceRoute.getStationsByRouteId(route_id);
        List<StationRequest> searched_stations1 = new ArrayList<>();
        for(StationRequest station : searched_stations)
            if(station.getId_route() == route_id)
                searched_stations1.add(station);
        return searched_stations1;
    }

    @RequestMapping (value = "/getAllStations",  method = RequestMethod.GET)
    public List<StationRequest> getAllStations(){
        return serviceRoute.getAllStations();
    }

    @RequestMapping(value = "/findRoute/{id_train}")
    public String findARouteByTrainId(@PathVariable("id_train") String id_train){
         return  serviceRoute.findARouteByTrainId(id_train).toString();
    }

    @RequestMapping(value = "/findTrain/{id_train}")
    public String findTrain(@PathVariable("id_train") String id_train){
         return serviceRoute.findTrainById(id_train);
    }

    @RequestMapping(value = "/find/{id_train}")
    public Route find(@PathVariable("id_train") String id_train){
        return  serviceRoute.find(id_train);
    }

    @RequestMapping (value = "/getAllRoutes",  method = RequestMethod.GET)
    public List<RouteRequestForBD> getAllRoutes(){
        return serviceRoute.getAllRoutes();
    }

    @RequestMapping(value = "/getSearchedRoutes/{departure}/{arrival}", method = RequestMethod.GET)
    public List<SearchedRoute> foundRoutes(@PathVariable("departure") String departure,
                                           @PathVariable("arrival") String arrival){
        return serviceRoute.searchDirectRoutes(departure, arrival);
    }

}
