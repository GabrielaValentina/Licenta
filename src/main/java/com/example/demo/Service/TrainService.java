package com.example.demo.Service;

import com.example.demo.Domain.Route;
import com.example.demo.Domain.StationOfARoute;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Repository.StationOfARouteRepository;
import com.example.demo.Service.Interface.ITrainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TrainService implements ITrainService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationOfARouteRepository stationOfARouteRepository;

    @Override
    public List<StationOfARoute> getAllStationsOfATrain(String train_id) {
        List<StationOfARoute> stations = new ArrayList<>();
        Route route = routeRepository.findByIdTrain(train_id);

        //if exists a valid route with this train
        if(!route.equals("")){
            Long id_route = route.getId_route();
            stations = stationOfARouteRepository.getAllStationsOfATrain(id_route);
        }

        return  stations;
    }

    @Override
    public Route findARouteByTrain(String train_id) {
        return null;
    }

    @Override
    public Route findARouteByDateAndDepartureAndDestination(Timestamp date, String departure, String destination) {
        return null;
    }

    @Override
    public List<Route> findAllRoutesIdByDateAndDepartureAndDestination(Timestamp date, String departure, String destination) {
        return null;
    }

    @Override
    public HashMap<Long, List<StationOfARoute>> findAllRoutesByDateAndDepartureAndDestination(Timestamp date, String departure, String destination) {
        return null;
    }
}
