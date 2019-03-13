package com.stackroute.restaurantservice.exception;

public class RestaurantAlreadyException extends Exception {

    String data;

    public RestaurantAlreadyException() {
    }

    public RestaurantAlreadyException(String data) {
        super(data);
        this.data = data;
    }
}
