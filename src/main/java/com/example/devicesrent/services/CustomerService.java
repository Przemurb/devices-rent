package com.example.devicesrent.services;

import com.example.devicesrent.data.Customer;
import com.example.devicesrent.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerService {
    private final Scanner scanner;
    private final CustomerRepository customerRepository;

    public CustomerService(Scanner scanner, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void add() {
        System.out.print("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Numer Dowodu Osobistego: ");
        String documentNumber = scanner.nextLine();
        Customer customer = new Customer(firstName, lastName, pesel, documentNumber);
        customerRepository.save(customer);
        System.out.println("Dodano użytkownika: " + customer);
    }

    @Transactional
    public void delete() {
        try {
            Customer customerToRemove = choseCustomer();
            if (customerToRemove.getRentDevices().isEmpty()) {
                System.out.println("Usunięto użytkownika " + customerToRemove);
                customerRepository.deleteById(customerToRemove.getId());
            } else {
                System.out.println("Użytkownik ma wypożyczone narzędzia! Nie można go usunąć!)");
            }
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Customer choseCustomer() throws CustomerException {
        if(customerRepository.count() > 1) {
            Optional<Customer> customer = Optional.empty();
            while (customer.isEmpty()) {
                System.out.println("Podaj numer użytkownika do usunięcia: ");
                printCustomerList();
                try {
                    customer = customerRepository.findById(scanner.nextLong());
                } catch (InputMismatchException e) {
                    customer = Optional.empty();
                    scanner.nextLine();
                }
            }
            return customer.get();
        } else {
            throw new CustomerException("Baza użytkowników jest pusta!");
        }
    }

    private void printCustomerList() {
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer.getId() + " - " + customer);
        }
    }
}

