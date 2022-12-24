package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(String id, Order order) {
        orderRepository.orderMap.put(id,order);
    }


    public void addPartner(String partnerId) {
        if(orderRepository.deliveryPartnerMap.containsKey(partnerId));
        else {
            DeliveryPartner deliveryPartner = null;
            deliveryPartner.setId(partnerId);
            deliveryPartner.setNumberOfOrders(0);

            orderRepository.deliveryPartnerMap.put(partnerId, deliveryPartner);
            orderRepository.partnerToOrderMap.put(partnerId,new ArrayList<>());
        }
    }

    public void assignPartnerToOrder(String orderId, String partnerId) {
        if(!orderRepository.deliveryPartnerMap.containsKey(partnerId)){
            DeliveryPartner deliveryPartner = null;
            deliveryPartner.setId(partnerId);
            deliveryPartner.setNumberOfOrders(0);

            orderRepository.deliveryPartnerMap.put(partnerId, deliveryPartner);
            orderRepository.partnerToOrderMap.put(partnerId,new ArrayList<>());
        }
     List<String> list=orderRepository.partnerToOrderMap.get(partnerId);
        list.add(orderId);
        orderRepository.partnerToOrderMap.put(partnerId,list);
       DeliveryPartner deliveryPartner= orderRepository.deliveryPartnerMap.get(partnerId);
       deliveryPartner.setNumberOfOrders(list.size());
       orderRepository.deliveryPartnerMap.put(partnerId,deliveryPartner);

    }

    public void assignOrderPartner(String orderId, String partnerId) {
        orderRepository.orderToParterMap.put(orderId,partnerId);

    }

    public Order getOrderById(String orderId) {
        Order order=orderRepository.orderMap.get(orderId);
        return order;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        DeliveryPartner deliveryPartner= orderRepository.deliveryPartnerMap.get(partnerId);
        return deliveryPartner;
    }

    public Integer getTotalOrdersNums(String partnerId) {

        List<String> list= orderRepository.partnerToOrderMap.get(partnerId);
        return list.size();
    }

    public List<String> getTotalOrders(String partnerId) {
        return orderRepository.partnerToOrderMap.get(partnerId);
    }

    public List<String> getAllOrders() {
        List<String> list= new ArrayList<>();
        if (!orderRepository.orderMap.isEmpty()){

            for (String id: orderRepository.orderMap.keySet()) {
                list.add(id);
            }
        }
        return list;
    }

    public Integer getCountOfUnassinedOrders() {
        int count=0;
        for(String s:orderRepository.orderMap.keySet()){
            if(!orderRepository.orderToParterMap.containsKey(s)){
                count++;
            }
        }
        return count;
    }
}
