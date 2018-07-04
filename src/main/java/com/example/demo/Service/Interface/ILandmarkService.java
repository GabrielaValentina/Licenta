package com.example.demo.Service.Interface;

import com.example.demo.Domain.Landmark;
import com.example.demo.Domain.Request.LandmarkRequest;
import com.example.demo.Domain.Request.LandmarkWithDetailsRequest;
import com.example.demo.Domain.Route;

import java.util.List;

public interface ILandmarkService {
    List<LandmarkRequest> getAllLandmarks();
    List<LandmarkWithDetailsRequest> getAllDetailsAboutLandmarks();
    List<Landmark> getAllLandmarksByRoute(Long route);
    Landmark addNewLandmark(float latitude, float longitude,
                                   String location, String description, Long id_route);
}
