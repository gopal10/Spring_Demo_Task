package com.stackroute.restaurantservice.controller;


import com.stackroute.restaurantservice.domain.Restaurant;
import com.stackroute.restaurantservice.service.RestaurantServiceImpl;
import com.stackroute.restaurantservice.exception.RestaurantAlreadyException;
import com.stackroute.restaurantservice.exception.RestaurantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin
public class RestaurantController {

    private RestaurantServiceImpl restaurantServiceImpl;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    /*
    This method will save restaurant data into the mysql database
     */
    @PostMapping("/restaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyException {
        ResponseEntity responseEntity;
        Restaurant res = null;
        res = restaurantServiceImpl.savedata(restaurant);
        return new ResponseEntity<Restaurant>(res, HttpStatus.CREATED);
    }

    /*
   This method will retrive restaurant data from the mysql database
    */
    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurant() {
        return new ResponseEntity<List<Restaurant>>(restaurantServiceImpl.getAllUsers(), HttpStatus.ACCEPTED);
    }

    /*
   This method will retrive restaurant data by id
    */
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> GetRestaurantByID(@PathVariable int id) {
        Optional<Restaurant> getRestaurant = restaurantServiceImpl.getById(id);
        return new ResponseEntity<Restaurant>(getRestaurant.get(), HttpStatus.OK);
    }

    /*
   This method will delete restaurant data by id
    */
    @GetMapping("/delete/{id}")
    public String deletegetRestaurant(@PathVariable int id) {
        restaurantServiceImpl.delete(id);
        return "data deleted";
    }

    /*
   This method will upadte restaurant data by id
    */
    @PutMapping("/update/{id}")
    public String upadtegetRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) throws RestaurantAlreadyException, RestaurantNotFound {
        restaurant.setRestaurantId(id);
        restaurantServiceImpl.updataData(restaurant);
        return "Updated successfully";

    }

    /*
   This method will retrive restaurant data using query parameter
    */
    @GetMapping("/query")
    public ResponseEntity<?> getQueryAll() {
        List<Restaurant> res = restaurantServiceImpl.getByQuery();
        return new ResponseEntity<List<Restaurant>>(res, HttpStatus.OK);
    }

    /*
    This method will retrive restaurant data using query parameter with name
   */
    @GetMapping("/query/{name}")
    public ResponseEntity getRestaurantByName(@PathVariable String name) throws RestaurantNotFound {
        List<Restaurant> res = null;
        res = restaurantServiceImpl.getbyname(name);
        return new ResponseEntity<List<Restaurant>>(res, HttpStatus.OK);
    }
}
