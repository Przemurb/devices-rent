package com.example.devicesrent.repository;

import com.example.devicesrent.data.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
