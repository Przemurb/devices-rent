package com.example.devicesrent.dao;

import com.example.devicesrent.data.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class CustomerDao {
    private final EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    public Optional<Customer> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        readById(customer.getId()).ifPresent(entityManager::remove);
    }
}
