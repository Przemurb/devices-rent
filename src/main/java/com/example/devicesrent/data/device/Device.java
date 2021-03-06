package com.example.devicesrent.data.device;

import com.example.devicesrent.data.category.Category;
import com.example.devicesrent.data.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Category category;
    @ManyToMany( cascade = CascadeType.PERSIST)
    private List<Customer> customers = new ArrayList<>();

    public Device(String name, String description, int quantity, double price, Category category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public void addCustomer (Customer customer) {
        customers.add(customer);
        customer.getRentDevices().add(this); //dodaje wypożyczone narzędzie do bazy!
    }

    @Override
    public String toString() {
        return name + " (" + description +
                ", " + quantity + " szt." +
                ", cena " + price +
                " zł), kategoria: " + category.getName();
    }
}
