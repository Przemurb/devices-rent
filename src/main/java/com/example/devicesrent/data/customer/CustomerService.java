package com.example.devicesrent.data.customer;

import com.example.devicesrent.data.device.Device;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
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
    public void addNewCustomer() {
        System.out.print("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        System.out.print("Numer Dowodu Osobistego: ");
        String documentNumber = scanner.nextLine();
        Customer customer = new Customer(firstName, lastName, pesel, documentNumber);
        customerRepository.save(customer);
        System.out.println("Dodano użytkownika: " + customer);
    }

    @Transactional
    public void deleteCustomer() {
        try {
            System.out.print("Usuwanie użytkownika");
            Customer customerToRemove = choseCustomerById();
            List<Device> rentDevices = customerToRemove.getRentDevices();
            if (rentDevices.isEmpty()) {
                System.out.println("Usunięto użytkownika " + customerToRemove);
                customerRepository.deleteById(customerToRemove.getId());
            } else {
                System.out.println("Użytkownik ma wypożyczone narzędzia! Nie można go usunąć!)");
            }
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer choseCustomerById() throws CustomerException {
        if (customerRepository.count() > 0) {
            Optional<Customer> customer = Optional.empty();
            while (customer.isEmpty()) {
                System.out.println("Podaj numer użytkownika: ");
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
            scanner.nextLine();
            throw new CustomerException("Baza użytkowników jest pusta!");
        }
    }

    public void findCustomerByPesel() {
        System.out.println("Podaj PESEL użytkownika: ");
        customerRepository.findByPeselIgnoreCase(scanner.nextLine())
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Brak użytkownika po podanym numerze PESEL"));
    }


    private void printCustomerList() {
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer.getId() + " - " + customer);
        }
    }
}

