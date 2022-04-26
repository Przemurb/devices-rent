package com.example.devicesrent.data.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<List<Device>> findDevicesByNameContainingIgnoreCase(String name);
    Optional<Device> findDeviceByNameIgnoreCase(String name);
}
