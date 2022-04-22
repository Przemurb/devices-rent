package com.example.devicesrent.data;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private int pesel;
    private String documentNumber;
    @ManyToMany
    private List<Device> rentDevices = new ArrayList<>();

    public Customer(String firstName, String lastName, int pesel, String documentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel=" + pesel +
                ", documentNumber='" + documentNumber + '\'' +
                ", devices=" + rentDevices +
                '}';
    }
}
