package com.sonu.movie_catalog_service.resources;

import com.sonu.movie_catalog_service.models.CatalogItem;
import com.sonu.movie_catalog_service.models.Movie;
import com.sonu.movie_catalog_service.models.Rating;
import com.sonu.movie_catalog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable ("userId") String userId){

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId , UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
           //For each movie ID, call movie info service and get details
            Movie movie= restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
            // Put them all together
             return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        })
                .collect(Collectors.toList());
    }
}

   //---Using the webclient ---//
                /*
                @Autowired
                private WebClient.Builder webClientBuilder;
                WebClient.Builder builder = WebClient.builder();
             /*
                 Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+ rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
             */