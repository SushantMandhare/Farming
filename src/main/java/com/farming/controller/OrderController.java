package com.farming.controller;

import com.farming.models.Order;
import com.farming.service.OrderService;
import com.farming.service.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public ProductService productService;

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody Order order){
        orderService.addOrder(order);
        productService.lessQuantity(order.getPid(),order.getQuantity());
        return ResponseEntity.ok().body("Order added successfully");
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<List<Order>> allOrder(@PathVariable String email){
        return ResponseEntity.ok().body(orderService.allOrders(email));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id){
        Order order=orderService.getOrder(id);
        orderService.deleteOrder(id);
        productService.addQuantity(order.getPid(),order.getQuantity());
        return ResponseEntity.ok().body("Deleted Successfully");
    }
}
