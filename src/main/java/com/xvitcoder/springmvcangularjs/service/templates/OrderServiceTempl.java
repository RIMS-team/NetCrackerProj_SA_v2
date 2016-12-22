package com.xvitcoder.springmvcangularjs.service.templates;


import com.xvitcoder.springmvcangularjs.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Elina on 01.12.2016.
 */
public interface OrderServiceTempl {
     boolean addOrder(Order order);

     List<Order> getOrdersByUser(User user);

     List<Order> getOrdersByEmployee(Employee employee);

     List<Order> getOrdersByDate(Date startDate, Date endDate);

     List<Order> getOrdersByStatus(String status);

     List<Order> getOrdersByNotification(int notificationType);

     boolean delOrder(OrderStatus orderStatus);

     Notification sendNotification(Order order, int notificationType);
}
