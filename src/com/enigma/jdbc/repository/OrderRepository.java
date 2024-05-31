package com.enigma.jdbc.repository;

import com.enigma.jdbc.dto.request.OrderRequest;
import com.enigma.jdbc.dto.response.OrderResponse;

import java.util.List;

public interface OrderRepository {
    void save(OrderRequest request);
    OrderResponse findById(Integer id);
    List<OrderResponse> findAll();
}
