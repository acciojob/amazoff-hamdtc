package com.driver;

import lombok.Data;

@Data

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        this.deliveryTime = Integer.parseInt(deliveryTime.substring(0, 2)) * 60 + Integer.parseInt(deliveryTime.substring(3, 5));
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }
}
