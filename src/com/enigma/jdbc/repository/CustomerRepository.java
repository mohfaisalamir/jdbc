package com.enigma.jdbc.repository;

import com.enigma.jdbc.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    void delete(int id);
    void update(Customer customer);
}
