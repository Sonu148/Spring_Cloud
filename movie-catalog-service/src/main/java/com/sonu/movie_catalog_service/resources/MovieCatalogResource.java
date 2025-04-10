package com.sonu.movie_catalog_service.resources;

import com.sonu.movie_catalog_service.models.CatalogItem;
import com.sonu.movie_catalog_service.models.Movie;
import com.sonu.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable ("userId") String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("131", 3),
                new Rating ("132", 4)
        );

        return ratings.stream().map(rating -> {
            Movie movie= restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
             return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        })
                .collect(Collectors.toList());
    }
}
