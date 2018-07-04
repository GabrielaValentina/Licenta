package com.example.demo.Controller;

import com.example.demo.Domain.Landmark;
import com.example.demo.Domain.Request.LandmarkRequest;
import com.example.demo.Domain.Request.LandmarkWithDetailsRequest;
import com.example.demo.Domain.Route;
import com.example.demo.Service.Interface.ILandmarkService;
import com.example.demo.Service.Interface.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/landmark")
public class LandmarkController {

    @Autowired
    ILandmarkService landmarkService;

    @Autowired
    IRouteService routeService;

    @RequestMapping(value = "/getAllLandmarks", method = RequestMethod.GET)
    public List<LandmarkRequest> getAll() {
        return landmarkService.getAllLandmarks();
    }

    @RequestMapping(value = "/getAllLandmarksWithDetails", method = RequestMethod.GET)
    public List<LandmarkWithDetailsRequest> getAllLandmarksWithDetails() {
        return landmarkService.getAllDetailsAboutLandmarks();
    }

    @RequestMapping(value = "/getAllLandmarks/{route_id}", method = RequestMethod.GET)
    public List<LandmarkRequest> getAllLandmarkByRouteId(@PathVariable("route_id") Long route_id) {
       // Route route = routeService.findRouteById(route_id);
        List<LandmarkRequest> landmarks = new ArrayList<>();
       List<Landmark> list = landmarkService.getAllLandmarksByRoute(route_id);
       for(Landmark landmark : list){
           landmarks.add(new LandmarkRequest(landmark.getLatitude(), landmark.getLongitude(),
                   landmark.getLocation(), landmark.getDescription(), landmark.getRoute()));
       }
       return landmarks;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Landmark addLandmark(@RequestBody LandmarkRequest landmark) {
        //System.out.println(landmark.getRoute_id() + "");
        return landmarkService.addNewLandmark(landmark.getLatitude(), landmark.getLongitude(),
                landmark.getLocation(), landmark.getDescription(), landmark.getRoute_id());
    }
}
