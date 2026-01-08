package com.eikka.karkafeernaWebScraper.controllers;

import com.eikka.karkafeernaWebScraper.components.Restaurant;
import com.eikka.karkafeernaWebScraper.service.RestaurantService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(("/api/v1"))
public class RestaurantInfoController {

    private final RestaurantService restaurantService;

    public RestaurantInfoController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public ResponseEntity<Resource> getMeals(){
        return restaurantService.getAllInfo();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getGql() throws IOException {
        return restaurantService.getRestaurants();
    }
}
