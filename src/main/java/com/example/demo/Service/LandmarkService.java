package com.example.demo.Service;

import com.example.demo.Domain.Landmark;
import com.example.demo.Domain.Request.LandmarkRequest;
import com.example.demo.Domain.Request.LandmarkWithDetailsRequest;
import com.example.demo.Domain.Route;
import com.example.demo.Domain.StationOfARoute;
import com.example.demo.Repository.LandmarkRepository;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Service.Interface.ILandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LandmarkService implements ILandmarkService {

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RouteService routeService;

    @Override
    public List<LandmarkRequest> getAllLandmarks() {
        List<Landmark> landmarks =  landmarkRepository.findAll();
        List<LandmarkRequest> landmarkRequests =  new ArrayList<>();
        for(Landmark landmark : landmarks){
            landmarkRequests.add(new LandmarkRequest(landmark.getLatitude(),
                    landmark.getLongitude(), landmark.getLocation(),
                    landmark.getDescription(), landmark.getRoute()));
        }
        return landmarkRequests;
    }

    @Override
    public List<LandmarkWithDetailsRequest> getAllDetailsAboutLandmarks() {
        List<LandmarkWithDetailsRequest> landmarks = new ArrayList<>();
        List<Landmark> allLandmarksList = landmarkRepository.findAll();
        String departure = "";
        String destination = "";
        List<StationOfARoute> stations;
        List<StationOfARoute> stationsList;
        for(Landmark landmark : allLandmarksList){
           // stations = landmark.getRoute().getStationOfARoutes();
            stations = routeService.getStationsByRoute(landmark.getRoute());
            stationsList = new ArrayList<StationOfARoute>(stations);
            Collections.sort(stationsList);
            departure = stationsList.get(0).getStationName();
            destination = stationsList.get(stationsList.size()-1).getStationName();
            System.out.println("id = " + landmark.getId() + " location = "  + landmark.getLocation());
            landmarks.add(new LandmarkWithDetailsRequest(landmark.getId(),
                    landmark.getLatitude(),
                    landmark.getLongitude(),
                    landmark.getLocation(),
                    landmark.getDescription(),
                    landmark.getRoute(),
                    departure, destination));
        }
        return landmarks;
    }

    @Override
    public List<Landmark> getAllLandmarksByRoute(Long id_route) {
        return landmarkRepository.findByRoute(id_route);
    }

    @Override
    public Landmark addNewLandmark(float latitude, float longitude, String location, String description, Long id_route) {
     //   Route route = new Route();
    //  try {
     //      route = routeRepository.findByIdRoute(id_route);
     //     Thread.sleep(500);
      //}catch (InterruptedException ex){}
      //  System.out.println( "id = " + id_route + "lat = "+latitude + "desc = "+description);
        return landmarkRepository.save(new Landmark(latitude, longitude, location, description, id_route));
    }
}
