package com.farming.service;

import com.farming.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order addOrder(Order order);
    public List<Order> allOrders(String email);
    public void deleteOrder(Integer id);
    public Order getOrder(Integer id);
    public void deleteByPID(Integer pid);
}
