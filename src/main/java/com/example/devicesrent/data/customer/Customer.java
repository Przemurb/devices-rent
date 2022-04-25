package com.example.devicesrent.data.customer;

import com.example.devicesrent.data.device.Device;
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
    private String pesel;
    private String documentNumber;
    @ManyToMany (mappedBy = "customers")
    private List<Device> rentDevices = new ArrayList<>();

    public Customer(String firstName, String lastName, String pesel, String documentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.documentNumber = documentNumber;
    }

    public void rentDevice (Device device) {
        rentDevices.add(device);
    }

    public void returnDevice (Device device) {
        rentDevices.remove(device);
    }
    @Override
    public String toString() {
        return firstName + " " +
                lastName +
                " (pesel: " + pesel +
                ", dokument: " + documentNumber +
                ")";
    }
}
