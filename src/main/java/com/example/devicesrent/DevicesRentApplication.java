package com.example.devicesrent;


import com.example.devicesrent.repository.DeviceRepository;
import com.example.devicesrent.app.ApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class DevicesRentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicesRentApplication.class, args);
        DeviceRepository deviceRepository = context.getBean(DeviceRepository.class);

//        Device device1 = new Device("Wiertarka udarowa", "Wiertarka udarowa 3000W", 1, 75.00, null);
//
//        Category category1 = new Category("Elektronarzędzia", "Elektronarzędzia ręczne");

//        Customer customer1 = new Customer();
//        customer1.setFirstName("Franek");
//        customer1.setLastName("Frankowski");
//        customer1.setPesel("12345875");
//        customer1.setDocumentNumber("AAA1232345");

//        device1.setCategory(category1);
//        device1.addCustomer(customer1);

//        deviceRepository.save(device1);

        ApplicationController applicationController = context.getBean(ApplicationController.class);
        applicationController.mainLoop();

        SpringApplication.exit(context);
    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }


}
