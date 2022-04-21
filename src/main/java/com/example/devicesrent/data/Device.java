package com.example.devicesrent.data;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int quantity;
    @ManyToOne
    private Category category;

    public Device(String name, String description, int quantity, Category category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
    }
}
