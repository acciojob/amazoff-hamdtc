package com.driver;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import jdk.jfr.DataAmount;
import lombok.Data;


@Data
public class DeliveryPartner {

    private String id;
    private int numberOfOrders;

    }