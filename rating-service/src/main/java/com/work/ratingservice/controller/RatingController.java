package com.work.ratingservice.controller;

import com.work.ratingservice.exception.RatingAlreadyExistsException;
import com.work.ratingservice.exception.RatingNotFoundException;
import com.work.ratingservice.model.Rating;
import com.work.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/books")
public class RatingController {

    @Autowired
    RatingService ratingService;


    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Rating>> getAllRatings(final HttpServletRequest req, HttpServletResponse res){
        return new ResponseEntity<List<Rating>>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRatingsByBookId(@PathVariable("id") long ratingId) {
        List<Rating> ratings = null;
        try{
          ratings = ratingService.getRatingsByBookId(ratingId);
        } catch(RatingNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Rating rating, @PathVariable("bookId") long ratingId){
        Rating rating1 = null;
        try{
            rating1 = ratingService.updateRating(rating,ratingId);
        } catch(RatingNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Rating>(rating1,HttpStatus.OK);
    }

    @PostMapping("/savebook")
    public ResponseEntity<?> saveBook(@RequestBody Rating rating){
        try{
            ratingService.createRating(rating);
        } catch(RatingAlreadyExistsException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Rating>(rating,HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") long bookId){

        try{
             ratingService.deleteBook(bookId);
        } catch(RatingNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Book Deleted Successfully",HttpStatus.OK);
    }

}
