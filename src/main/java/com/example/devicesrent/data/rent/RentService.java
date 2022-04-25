package com.example.devicesrent.data.rent;

import com.example.devicesrent.data.customer.Customer;
import com.example.devicesrent.data.customer.CustomerException;
import com.example.devicesrent.data.customer.CustomerService;
import com.example.devicesrent.data.device.Device;
import com.example.devicesrent.data.device.DeviceException;
import com.example.devicesrent.data.device.DeviceService;
import com.example.devicesrent.repository.CustomerRepository;
import com.example.devicesrent.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentService {
    private final Scanner scanner;
    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;
    private final CustomerService customerService;

    public RentService(Scanner scanner,
                       DeviceRepository deviceRepository,
                       DeviceService deviceService,
                       CustomerService customerService, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
        this.customerService = customerService;
    }
    @Transactional
    public void rentDevice() {
        System.out.println("WYPOŻYCZALNIA");
        try {
            Customer customer = customerService.choseCustomer();
            Device device = deviceService.choseDevice();
            if (device.getQuantity() > 0) {
                customer.rentDevice(device);
                device.setQuantity(device.getQuantity() - 1);
                System.out.println("Wypożyczono " + device.getName() + " dla " + customer.getFirstName() + " "
                        + customer.getLastName());
            } else {
                throw new DeviceException("Brak dostępnego narzędzia. Wszystkie są wypożyczone.");
            }
        } catch (CustomerException | DeviceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void returnDevice () {
        System.out.println("ZWROT NARZĘDZI");
        try {
            Customer customer = customerService.choseCustomer();
            Device device = returnDevice(customer);
            customer.returnDevice(device);
            device.setQuantity(device.getQuantity() + 1);
            System.out.println("Zwrócono narzędzie: " + device.getName());
            double payment = device.getPrice();
            System.out.println("Do zapłaty: " + payment + " zł.\n");
        } catch (CustomerException | DeviceException e) {
            System.out.println(e.getMessage());
        }
    }

    private Device returnDevice(Customer customer) throws DeviceException {
        Optional<Device> returnDevice = Optional.empty();
        if (customer.getRentDevices().size() > 0) {
            while (returnDevice.isEmpty()) {
                printDevicesList(customer);
                System.out.print("Podaj numer zwracanego narzędzia: ");
                try {
                    returnDevice = deviceRepository.findById(scanner.nextLong());
                } catch (InputMismatchException e) {
                    returnDevice = Optional.empty();
//                    scanner.nextLine();
                }
            }
            return returnDevice.get();
        } else {
            throw new DeviceException("Brak wypożyczonych narzędzi przez tego użytkownika.");
        }
    }

    private void printDevicesList(Customer customer) {
        System.out.println("Wypożyczone narzędzia:");
        customer.getRentDevices().forEach(d -> System.out.println(d.getId() + " - " + d.getName()));
    }
}