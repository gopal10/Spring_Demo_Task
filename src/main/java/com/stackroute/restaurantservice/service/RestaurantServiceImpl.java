package com.stackroute.restaurantservice.service;

import com.stackroute.restaurantservice.domain.Restaurant;
import com.stackroute.restaurantservice.repository.RestaurantRepository;
import com.stackroute.restaurantservice.exception.RestaurantAlreadyException;
import com.stackroute.restaurantservice.exception.RestaurantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurant_repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurant_repository) {
        this.restaurant_repository = restaurant_repository;
    }

    public RestaurantServiceImpl() {
    }

    /*
    This method will save restaurant data into the mysql database
     */
    @Override
    public Restaurant savedata(Restaurant restaurant) throws RestaurantAlreadyException {
        if (restaurant_repository.existsById(restaurant.getRestaurantId())) {
            throw new RestaurantAlreadyException("Restaurant already present");
        }
        Restaurant restaurant1 = (Restaurant) restaurant_repository.save(restaurant);
        return restaurant1;
    }

    /*
    This method will upadte restaurant data into the mysql database
     */
    @Override
    public Restaurant updataData(Restaurant restaurant) throws RestaurantNotFound {
        if (!restaurant_repository.existsById(restaurant.getRestaurantId())) {
            throw new RestaurantNotFound("Restaurant Not Found");
        }
        Restaurant restaurant1 = (Restaurant) restaurant_repository.save(restaurant);
        return restaurant1;
    }

    /*
    This method will retrive all  restaurant data
     */
    @Override
    public List<Restaurant> getAllUsers() {
        return (List<Restaurant>) restaurant_repository.findAll();
    }

    /*
     This method will retrive all  restaurant data by id
     */
    public Optional<Restaurant> getById(int id) {
        Optional<Restaurant> getUser = restaurant_repository.findById(id);
        return getUser;
    }

    /*
   This method will delete restaurant data by id
    */
    @Override
    public void delete(int id) {
        restaurant_repository.deleteById(id);
    }

    /*
   This method will retrive restaurant data using query parameter
    */
    @Override
    public List<Restaurant> getByQuery() {
        return restaurant_repository.findalluser();
    }

    /*
     This method will retrive restaurant data using query parameter with name
       */
    @Override
    public List<Restaurant> getbyname(String name) throws RestaurantNotFound {
        if (restaurant_repository.findByName(name).isEmpty()) {
            throw new RestaurantNotFound("Restaurant Not Found");
        }
        List<Restaurant> restaurant1 = restaurant_repository.findByName(name);
        return restaurant1;
    }
}
