package com.farming.repository;

import com.farming.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByEmail(String email);
    public void deleteByPid(Integer pid);
}
