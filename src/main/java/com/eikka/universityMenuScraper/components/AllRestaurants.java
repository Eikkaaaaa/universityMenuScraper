package com.eikka.universityMenuScraper.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class AllRestaurants {

    private final String updatedAt;
    private final LinkedHashSet<Restaurant> restaurants;
    private final String[] sources = {
            "https://www.karkafeerna.fi",
            "https://www.unica.fi/en/restaurants/university-campus/assarin-ullakko/",
            "https://www.unica.fi/en/restaurants/university-campus/galilei/",
            "https://www.unica.fi/en/restaurants/university-campus/macciavelli/",
            "https://www.unica.fi/en/restaurants/university-campus/monttu-ja-mercatori/",
            "https://www.unica.fi/en/restaurants/kupittaa-campus/deli-pharma/",
            "https://www.unica.fi/en/restaurants/kupittaa-campus/delica/",
            "https://www.unica.fi/en/restaurants/kupittaa-campus/dental/",
            "https://www.unica.fi/en/restaurants/kupittaa-campus/kisalli/",
            "https://www.unica.fi/en/restaurants/kupittaa-campus/linus/",
            "https://www.unica.fi/en/restaurants/art-campus/sigyn/",
            "https://www.unica.fi/en/restaurants/others/unican-kulma/",
            "https://www.unica.fi/en/restaurants/others/fabrik-cafe/",
            "https://www.unica.fi/en/restaurants/others/piccu-maccia/",
            "https://www.unica.fi/en/restaurants/others/puutorin-nurkka/",
            "https://www.unica.fi/en/restaurants/other-restaurants/henkilostoravintola-waino/",
            "https://www.unica.fi/en/restaurants/other-restaurants/kaffeli/",
            "https://www.unica.fi/en/restaurants/other-restaurants/kaivomestari/",
            "https://www.unica.fi/en/restaurants/other-restaurants/lemminkainen/",
            "https://www.unica.fi/en/restaurants/other-restaurants/mairela/",
            "https://www.unica.fi/en/restaurants/other-restaurants/rammeri/",
            "https://www.unica.fi/en/restaurants/other-restaurants/ruokakello/"
    };

    public AllRestaurants() {
        this.restaurants = new LinkedHashSet<>();
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void addRestaurants(LinkedHashSet<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
    }

    public LinkedHashSet<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public String toString() {
        return "AllRestaurants{" +
                "updatedAt='" + updatedAt + '\'' +
                ", restaurants=" + restaurants +
                ", sources=" + Arrays.toString(sources) +
                '}';
    }
}
