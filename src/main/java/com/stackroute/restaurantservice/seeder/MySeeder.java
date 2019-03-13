package com.stackroute.restaurantservice.seeder;


import com.stackroute.restaurantservice.domain.Restaurant;
import com.stackroute.restaurantservice.service.RestaurantServiceImpl;
import com.stackroute.restaurantservice.exception.RestaurantAlreadyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MySeeder implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    @Autowired
    RestaurantServiceImpl restaurantServiceImpl;

    public MySeeder(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    public RestaurantServiceImpl getRestaurantServiceImpl() {
        return restaurantServiceImpl;
    }

    public void setRestaurantServiceImpl(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @Value("${name1}")
    String name1;

    @Value("${location1}")
    String location1;

    @Value("${image1}")
    String image1;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Restaurant restaurant = new Restaurant(0, name1, location1, image1);
        try {
            restaurantServiceImpl.savedata(restaurant);
        } catch (RestaurantAlreadyException e) {
            e.printStackTrace();
        }
    }

    @Value("${name}")
    String name;

    @Value("${location}")
    String location;

    @Value("${image}")
    String image;

    @Override
    public void run(String... args) throws Exception {
        Restaurant restaurant = new Restaurant(0, name, location, image);
        try {
            restaurantServiceImpl.savedata(restaurant);
        } catch (RestaurantAlreadyException e) {
            e.printStackTrace();
        }
    }
}
