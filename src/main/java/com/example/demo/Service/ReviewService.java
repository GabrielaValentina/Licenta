package com.example.demo.Service;

import com.example.demo.Domain.Request.RecommendedRouteRequest;
import com.example.demo.Domain.Request.ReviewRouteRequest;
import com.example.demo.Domain.Review;
import com.example.demo.Domain.Route;
import com.example.demo.Domain.StationOfARoute;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Repository.StationOfARouteRepository;
import com.example.demo.Service.Interface.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;
@Service
public class ReviewService implements IReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationOfARouteRepository stationOfARouteRepository;

    private HashMap<Long, Integer> numberOfCommonRoutes = new HashMap<>();
    private HashMap<Long, Double> distances = new HashMap<>();
    private float arithmeticAverageOfCommonRoutes;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    @Override
    public Review addNewReview(Long id_user, String user_name, String comment, Double numberOfStars, String review_type,
                               String param, String time) {
        Timestamp timest = Timestamp.valueOf(time);
        return reviewRepository.save(new Review(id_user, user_name, comment,numberOfStars, review_type, param, timest));
    }

    @Override
    public List<Review> getAllReviewsByReviewType(String review_type) {
        return reviewRepository.getAllByReviewType(review_type);
    }

    @Override
    public  List<Review> getAllCommonReviews(Long id_user){
        int number_of_routes;
        numberOfCommonRoutes = new HashMap<>();
        List<Review> all_reviews = reviewRepository.getAllByReviewType("review_for_route");
        List<Review> commom_reviews = new ArrayList<>();
        List<Review> user_reviews = reviewRepository.getAllByUserId(id_user);
        for(Review review : all_reviews){
            if(review.getUser_id() != id_user){
                for(Review user_review : user_reviews)
                   if(review.getDetails().equals(user_review.getDetails()))
                       if(commom_reviews.contains(review) == false) {
                           commom_reviews.add(review);
                           if (numberOfCommonRoutes.containsKey(review.getUser_id()) == true) {
                               number_of_routes = numberOfCommonRoutes.get(review.getUser_id());
                               number_of_routes++;
                               numberOfCommonRoutes.put(review.getUser_id(), number_of_routes);
                           } else {
                               numberOfCommonRoutes.put(review.getUser_id(), 1);
                           }
                       }
            }
        }
        int sum = 0;
        for(Long user : numberOfCommonRoutes.keySet()){
             sum += numberOfCommonRoutes.get(user);
        }
        for (Long s :  numberOfCommonRoutes.keySet()){
            System.out.println("user : " + s + " rute comune : " + numberOfCommonRoutes.get(s));
        }
        arithmeticAverageOfCommonRoutes = (float) sum/numberOfCommonRoutes.size();
        System.out.println("media = " + arithmeticAverageOfCommonRoutes );
        return commom_reviews;
    }

    //adaug doar review-urile utilizatorilor cu care am rute in comun, iar numarul acestora este peste medie
    HashMap<Long, List<Review>> getAllReviewsWithNumberOfCommonReviewsGreaterThatAvarage(Long id_user){
        List<Review> allReviews = getAllCommonReviews(id_user);
        System.out.println("medie in functie : " + arithmeticAverageOfCommonRoutes);
        List<Review> list = new ArrayList<>();
        HashMap<Long, List<Review>> hashMap = new HashMap<>();
        for(Long user : numberOfCommonRoutes.keySet()){
            list = new ArrayList<>();
            if(numberOfCommonRoutes.get(user) >= arithmeticAverageOfCommonRoutes){
                System.out.println("rute comune : " + numberOfCommonRoutes.get(user));
                for(Review review : allReviews){
                    if(review.getUser_id() == user)
                        list.add(review);
                }
            }
            hashMap.put(user, list);
        }
        return hashMap;
    }


   @Override
    public void calculateDistances(Long user_id){
        distances = new HashMap<>();
        HashMap<Long, List<Review>> reviews = getAllReviewsWithNumberOfCommonReviewsGreaterThatAvarage(user_id);
        List<Review> listOfUserReviews = reviewRepository.getAllByUserId(user_id);
        double distance = 0;
        int sum = 0;
        for(Long user : reviews.keySet()){
            distance = 0;
            sum = 0;
            int sum_ver = -1;
            for(Review review : reviews.get(user)){
                for(Review review_user : listOfUserReviews){
                    if(review.getDetails().equals(review_user.getDetails()))
                        sum += (review.getNumberOfStars() - review_user.getNumberOfStars()) *
                                (review.getNumberOfStars() - review_user.getNumberOfStars());
                    sum_ver = sum;
                }

            }
            if(sum_ver != -1){
            distance = Math.sqrt(sum);
            distances.put(user, distance);}
        }
        for(Long user : distances.keySet())
            System.out.println("user : " + user + "distances "+ distances.get(user));
   }

    @Override
    public List<Route> getAllRecommendedRoutes(Long user_id){
        calculateDistances(user_id);
        List<Review> user_reviews = new ArrayList<>();
        List<Route> user_routes = new ArrayList<>();
        List<Route> recommended_routes = new ArrayList<>();
        List<Review> current_user_reviews = reviewRepository.getAllByUserId(user_id);
        List<Long> id_reviews = new ArrayList<>();
        for(Review r : current_user_reviews)
            if(!id_reviews.contains(Long.parseLong(r.getDetails())))
                id_reviews.add(Long.parseLong(r.getDetails()));
        Route route = new Route();
        if (distances.size() > 0){
            if(distances.size() <= 3){
                System.out.println("n<=3");
                for(Long user : distances.keySet()){
                    user_reviews = reviewRepository.getAllByUserId(user);
                    for(Review review : user_reviews){
                       if(!id_reviews.contains(Long.parseLong(review.getDetails()))){
                            route = routeRepository.findByIdRoute(Long.parseLong(review.getDetails()));
                            if(!recommended_routes.contains(route))
                              recommended_routes.add(route);
                        }
                    }
                }
            }
            else{
                //primele 3 valori cele mai mici si care au rute necomune cu utilizatorul curent
                distances = distances.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                        (e1, e2) -> e1, LinkedHashMap::new));
                for(Long user : distances.keySet()){
                    System.out.println("afis : " + user + "-"+distances.get(user));
                }
                int ct = 0;
               for(Long user : distances.keySet()){
                   System.out.println("n>3");
                   if(ct <= 2) {
                       System.out.println("user id = "+user + "dist = " + distances.get(user));
                       if(distances.get(user) != Double.parseDouble(user_id+"")) {
                           user_reviews = reviewRepository.getAllByUserId(user);
                           for (Review review : user_reviews) {
                               if (!id_reviews.contains(Long.parseLong(review.getDetails()))) {
                                   route = routeRepository.findByIdRoute(Long.parseLong(review.getDetails()));
                                   if (!recommended_routes.contains(route))
                                       recommended_routes.add(route);
                               }
                           }
                           ct++;
                       }
                   }

                }
            }
        }
        return recommended_routes;
    }

    @Override
    public List<RecommendedRouteRequest> getAllRecommendedRoutesRequest(Long user_id) {
        List<RecommendedRouteRequest> recommendedRoutes = new ArrayList<>();
        List<Route> routes = getAllRecommendedRoutes(user_id);
        RecommendedRouteRequest recommendedRoute = new RecommendedRouteRequest();
        List<StationOfARoute> stations = new ArrayList<>();
        String departure = "";
        String destination = "";
        for(Route route : routes) {
            if (route != null) {
                System.out.println("route id = " + route.getId_route());
                stations = stationOfARouteRepository.getAllStationsByRouteId(route.getId_route());
                departure = stations.get(0).getStationName();
                destination = stations.get(stations.size() - 1).getStationName();
                recommendedRoute = new RecommendedRouteRequest(route.getId_route(), route.getId_train(),
                        departure, destination);
                recommendedRoutes.add(recommendedRoute);
            }
        }
        for(RecommendedRouteRequest route : recommendedRoutes)
            System.out.println("rute recomandate = " + route.getId_route() + "  " + route.getDeparture()
            + "  " + route.getDestination() + "  " + recommendedRoute.getTrain());
        return recommendedRoutes;
    }

    @Override
    public List<ReviewRouteRequest> getAllRoutesReviews(){
        List<ReviewRouteRequest> list = new ArrayList<>();
        List<Review> reviews = reviewRepository.getAllByReviewType("review_for_route");
        Route route;
        ReviewRouteRequest routeRequest;
        List<StationOfARoute> stations = new ArrayList<>();
        for(Review review : reviews){
            route = routeRepository.findByIdRoute(Long.parseLong(review.getDetails()));
            routeRequest = new ReviewRouteRequest();
            stations = stationOfARouteRepository.getAllStationsByRouteId(Long.parseLong(review.getDetails()));
            routeRequest.setDeparture(stations.get(0).getStationName());
            routeRequest.setArrival(stations.get(stations.size()-1).getStationName());
            routeRequest.setComment(review.getComment());
            routeRequest.setStars(review.getNumberOfStars());
            routeRequest.setTime(review.getTime() + "");
            routeRequest.setName(review.getUserName());
            list.add(routeRequest);
        }
        return list;
    }
}
