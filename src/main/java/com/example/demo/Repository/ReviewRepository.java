package com.example.demo.Repository;

import com.example.demo.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM REVIEW", nativeQuery = true)
    List<Review> getAllReviews();
    List<Review> getAllByReviewType(String review_type);
    List<Review> getAllByUserId(Long user_id);
}
