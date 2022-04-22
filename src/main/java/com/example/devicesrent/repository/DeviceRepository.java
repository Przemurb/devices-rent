package com.example.devicesrent.repository;

import com.example.devicesrent.data.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {
}
