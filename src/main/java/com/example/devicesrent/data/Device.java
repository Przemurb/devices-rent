package com.example.devicesrent.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Category category;
    @ManyToMany(mappedBy = "rentDevices", cascade = CascadeType.PERSIST)
    private List<Customer>customers = new ArrayList<>();

    public Device(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public void addCustomer (Customer customer) {
        customers.add(customer);
        customer.getRentDevices().add(this); //dodaje wypożyczone narzędzie do bazy!

    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category=" + category +
                ", customers=" + customers +
                '}';
    }
}
