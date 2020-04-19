package com.work.ratingservice.service;


import com.work.ratingservice.exception.RatingAlreadyExistsException;
import com.work.ratingservice.exception.RatingNotFoundException;
import com.work.ratingservice.model.Rating;
import com.work.ratingservice.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepo ratingRepo;

    public boolean createRating( Rating rating) throws RatingAlreadyExistsException{
        final Optional<Rating> rating1 = ratingRepo.findById(rating.getId());
        if (rating1.isPresent()){
            throw new RatingAlreadyExistsException("Rating with this id already exists.");
        }
        ratingRepo.save(rating);
        return true;
    }


    public Rating updateRating(Rating rating, Long id) throws RatingNotFoundException {
        final Optional<Rating> rating1 = ratingRepo.findById(id);
        if(!rating1.isPresent()){
            throw new RatingNotFoundException("Rating with this id not found");
        }
        Rating rating2 = rating1.get();
        rating2.setId(rating.getId());
        rating2.setBookId(rating.getBookId());
        rating2.setStars(rating.getStars());
        return rating2;
    }

    public Boolean deleteBook(Long ratingId) throws RatingNotFoundException{
        final Optional<Rating> rating1 = ratingRepo.findById(ratingId);
        if(!rating1.isPresent()){
            throw new RatingNotFoundException("Book with this id not found");
        }
        ratingRepo.deleteById(ratingId);
        return true;
    }

    public List<Rating> getRatingsByBookId(Long bookId) throws RatingNotFoundException{

        final Optional<List<Rating>> rating1 = ratingRepo.findRatingsByBookId(bookId);
        if(!rating1.isPresent()){
            throw new RatingNotFoundException("Book with this id not found");
        }
        return rating1.get();
    }

    public List<Rating> getAllRatings(){
        return ratingRepo.findAll();
    }

}
