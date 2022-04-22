package com.example.devicesrent.dao;

import com.example.devicesrent.data.Device;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class DeviceDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public DeviceDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Device device) {
        entityManager.persist(device);
    }

    public Optional<Device> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Device.class, id));
    }

    @Transactional
    public void update(Device device) {
        entityManager.merge(device);
    }

    @Transactional
    public void delete(Device device) {
        readById(device.getId()).ifPresent(entityManager::remove);
    }
}
