package com.example.devicesrent.data.customer;

import com.example.devicesrent.data.customer.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Optional<Customer> findByPeselIgnoreCase(String pesel);
}
