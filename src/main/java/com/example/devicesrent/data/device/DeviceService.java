package com.example.devicesrent.data.device;

import com.example.devicesrent.data.category.Category;
import com.example.devicesrent.repository.CategoryRepository;
import com.example.devicesrent.repository.DeviceRepository;
import com.example.devicesrent.data.category.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceService {
    private final Scanner scanner;
    private final DeviceRepository deviceRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public DeviceService(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public void delete() {
        try {
            System.out.print("Usuwanie - ");
            Device deviceToRemove = choseDevice();
            if (deviceToRemove.getQuantity() > 0) {
                deviceToRemove.setQuantity(deviceToRemove.getQuantity() - 1);
                deviceRepository.save(deviceToRemove);
                System.out.println("Usunięto 1 szt. narzędzia. Pozostało " + deviceToRemove.getQuantity() + " szt.");
            } else {
                deviceRepository.deleteById(deviceToRemove.getId());
                System.out.println("Usunięto ostatnią sztukę narzędzia.");
            }
        } catch (DeviceException e) {
            System.out.println(e.getMessage());
        }
    }

    public Device choseDevice() throws DeviceException {
        Optional<Device> deviceToRemove = Optional.empty();
        if (deviceRepository.count() > 0) {
            while (deviceToRemove.isEmpty()) {
                printDevicesList();
                System.out.print("Podaj numer narzędzia: ");
                try {
                    deviceToRemove = deviceRepository.findById(scanner.nextLong());
                } catch (InputMismatchException e) {
                    deviceToRemove = Optional.empty();
                    scanner.nextLine();
                }
            }
            return deviceToRemove.get();
        } else {
            throw new DeviceException("Brak narzędzi!");
        }
    }

    private void printDevicesList() {
        for (Device device : deviceRepository.findAll()) {
            System.out.println(device.getId() + " - " + device.getName());
        }
    }

    @Transactional
    public void add() {
        System.out.print("Podaj nazwę narzędzia: ");
        String name = scanner.nextLine();
        System.out.print("Opis narzędzia: ");
        String description = scanner.nextLine();
        System.out.print("Ilość narzędzi: ");
        int quantity = scanner.nextInt();
        System.out.print("Cena: ");
        double price = scanner.nextDouble();
        Category category = choseCategory();
        Device device = new Device(name, description, quantity, price, category);
        deviceRepository.save(device);
        System.out.println("Dodano narzędzie: " + device);
    }

    private Category choseCategory() {
        if (categoryRepository.count() == 0) {
            scanner.nextLine();
            categoryService.add();
        }
            Optional<Category> category = Optional.empty();
            while (category.isEmpty()) {
                System.out.println("Do której kategorii przypisać narzędzie?");
                printCategoryList();
                try {
                    category = categoryRepository.findById(scanner.nextLong());
                } catch (InputMismatchException e) {
                    category = Optional.empty();
                    scanner.nextLine();
                }
            }
            return category.get();
        }

    private void printCategoryList() {
        for (Category category : categoryRepository.findAll()) {
            System.out.println(category.getId() + " - " + category);
        }
    }
}
