package com.farming.service;

import com.farming.models.Order;
import com.farming.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    public OrderRepository orderRepository;
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> allOrders(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.delete(id);
    }

    @Override
    public Order getOrder(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void deleteByPID(Integer pid) {
        orderRepository.deleteByPid(pid);
    }
}
