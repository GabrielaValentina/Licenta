package com.example.demo.Controller;

import com.example.demo.Domain.StationOfARoute;
import com.example.demo.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/train")
public class TrainController {

    @Autowired
    TrainService trainService;

    @RequestMapping(value = "/getAllStationsOfATrain/{train_id}")
    public List<StationOfARoute> getAllStationsOfATrain(@PathVariable("train_id") String train_id){
        return trainService.getAllStationsOfATrain(train_id);
    }
}
