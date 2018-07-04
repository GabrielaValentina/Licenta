package com.example.demo.Service.Interface;

import com.example.demo.Domain.Request.RecommendedRouteRequest;
import com.example.demo.Domain.Request.ReviewRouteRequest;
import com.example.demo.Domain.Review;
import com.example.demo.Domain.Route;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews();
    Review addNewReview(Long id_user, String user_name,
                        String comment, Double numberOfStars,
                        String review_type, String param, String time);
    List<Review> getAllReviewsByReviewType(String review_type);
    List<Review> getAllCommonReviews(Long id_user);
    void calculateDistances(Long user_id);
    List<Route> getAllRecommendedRoutes(Long user_id);
    List<RecommendedRouteRequest> getAllRecommendedRoutesRequest(Long user_id);
    List<ReviewRouteRequest> getAllRoutesReviews();
}
