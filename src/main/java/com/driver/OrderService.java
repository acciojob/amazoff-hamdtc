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

    public Integer getOrderLeftAfterTimeByPartner(String time, String partnerId) {
        int count=0;
        int t=Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        List<String> list=orderRepository.partnerToOrderMap.get(partnerId);
        for(String s:list){
            Order order=orderRepository.orderMap.get(s);
            if(t<order.getDeliveryTime()) count++;
        }
        return count;
    }

    public String getTimeOfLastDelivery(String partnerId) {
        List<String> list=orderRepository.partnerToOrderMap.get(partnerId);
        int t=0;
        for(String s:list){
            Order order=orderRepository.orderMap.get(s);
            if(t<order.getDeliveryTime()) t=order.getDeliveryTime();
        }
        String time="";
        if (t>0){

            int MM=t%60;
            int HH=(t-MM)/60;
            time=String.valueOf(HH)+":"+String.valueOf(MM);
        }
        return time;
    }

    public void deletePartner(String partnerId) {

//       if(orderRepository.deliveryPartnerMap.containsKey(partnerId))
//           orderRepository.deliveryPartnerMap.remove(partnerId);
//       if(orderRepository.partnerToOrderMap.containsKey(partnerId)){
//            List<String> list = orderRepository.partnerToOrderMap.get(partnerId);
//            orderRepository.partnerToOrderMap.remove(partnerId);
//            for(String s:list){
//                orderRepository.orderToParterMap.put(s,null);
//            }
//        }


    }

    public void deleteOrder(String orderId) {

//        if(orderRepository.orderMap.containsKey(orderId)) orderRepository.orderMap.remove(orderId);
//
//        String partner=orderRepository.orderToParterMap.get(orderId);
//
//        if(orderRepository.orderToParterMap.containsKey(orderId)) orderRepository.orderToParterMap.remove(orderId);
//
//        if(orderRepository.partnerToOrderMap.containsKey(partner)) {
//            List<String> list = orderRepository.partnerToOrderMap.get(partner);
//            if (list.contains(partner)) list.remove(partner);
//            orderRepository.partnerToOrderMap.put(partner, list);
//        }
    }
}
