package com.work.ratingservice.repository;


import com.work.ratingservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RatingRepo extends JpaRepository<Rating,Long> {

    Optional<List<Rating>> findRatingsByBookId(Long bookId);
}
