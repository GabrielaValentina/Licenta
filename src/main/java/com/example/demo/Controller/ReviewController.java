package com.example.demo.Controller;

import com.example.demo.Domain.Request.RecommendedRouteRequest;
import com.example.demo.Domain.Request.ReviewRequest;
import com.example.demo.Domain.Request.ReviewRouteRequest;
import com.example.demo.Domain.Review;
import com.example.demo.Domain.Route;
import com.example.demo.Service.Interface.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @RequestMapping(value = "/getReviews", method = RequestMethod.GET)
    public List<Review> getAll() {
        return reviewService.getAllReviews();
    }

    @RequestMapping(value = "/addNewReview", method = RequestMethod.POST)
    public Review addNewReview(@RequestBody ReviewRequest request){
        System.out.println("review -> " + request.getTime());
        return reviewService.addNewReview(request.getId_user(), request.getName(),
                request.getComment(),request.getNumberOfStars(), request.getReview_type(),
                request.getReview_param(), request.getTime());
    }

    @RequestMapping(value = "/getAllRecommendedRoutes/{id_user}")
    public List<RecommendedRouteRequest> getAllReviewsByType(@PathVariable("id_user") String id_user){
        Long id = Long.parseLong(id_user);
        System.out.println("id_user = " + id);
        return reviewService.getAllRecommendedRoutesRequest(id);
    }

    @RequestMapping(value = "/getAllRoutesReviews", method = RequestMethod.GET)
    public List<ReviewRouteRequest> getAllRoutesReviews(){
        return reviewService.getAllRoutesReviews();
    }

    @RequestMapping(value = "/getAllTrainsReviews", method = RequestMethod.GET)
    public List<Review> getAllTrainsReviews(){
        return reviewService.getAllReviewsByReviewType("review_for_train");
    }
}
