package com.stackroute.restaurantservice.exception;

public class RestaurantNotFound extends Exception {
    public RestaurantNotFound() {
    }

    public RestaurantNotFound(String message) {
        super(message);
    }
}
