package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    Map<String,DeliveryPartner> deliveryPartnerMap=new HashMap<>();
    Map<String,Order> orderMap=new HashMap<>();
    Map<String, List<String>> partnerToOrderMap=new HashMap<>();
    Map<String, String> orderToParterMap=new HashMap<>();







}
