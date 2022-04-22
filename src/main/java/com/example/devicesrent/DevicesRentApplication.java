package com.example.devicesrent;


import com.example.devicesrent.dao.DeviceDao;
import com.example.devicesrent.data.Category;
import com.example.devicesrent.data.Customer;
import com.example.devicesrent.data.Device;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DevicesRentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicesRentApplication.class, args);
        DeviceDao deviceDao = context.getBean(DeviceDao.class);

        Device device1 = new Device("Wiertarka udarowa", "Wiertarka udarowa 3000W", 3, 75.00);

        Category category1 = new Category("Elektronarzędzia", "Elektronarzędzia ręczne");

        Customer customer1 = new Customer();
        customer1.setFirstName("Franek");
        customer1.setLastName("Frankowski");
        customer1.setPesel(12345875);
        customer1.setDocumentNumber("AAA1232345");

        device1.setCategory(category1);
        device1.addCustomer(customer1);

        deviceDao.save(device1);



    }

}
