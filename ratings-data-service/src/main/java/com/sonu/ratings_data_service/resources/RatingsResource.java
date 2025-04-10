package com.sonu.ratings_data_service.resources;
import com.sonu.ratings_data_service.models.Rating;
import com.sonu.ratings_data_service.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
        public Rating getRating(@PathVariable("movieId") String movieId) {
            return new Rating(movieId, 4);
        }
    @RequestMapping("users/{userId}")
       public UserRating getUserRating (@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("131", 3),
                new Rating ("132", 4)
        );
        UserRating userRating= new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
       }
    }

