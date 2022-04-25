package com.example.devicesrent.app;

import com.example.devicesrent.data.category.CategoryService;
import com.example.devicesrent.data.customer.CustomerService;
import com.example.devicesrent.data.device.DeviceService;
import com.example.devicesrent.data.rent.RentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ApplicationController {
    private final Scanner scanner;
    private final DeviceService deviceService;
    private final CategoryService categoryService;
    private final CustomerService customerService;
    private final RentService rentService;

    public ApplicationController(Scanner scanner, DeviceService deviceService, CategoryService categoryService, CustomerService customerService, RentService rentService) {
        this.scanner = scanner;
        this.deviceService = deviceService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    public void mainLoop() {
        System.out.println("WYPOŻYCZALNIA NARZĘDZI");
        OptionMenu option = null;
        while (option != OptionMenu.EXIT) {
            printMenu();
            option = choseOption();
            assert option != null;
            executeOption(option);
        }
    }

    private void executeOption(OptionMenu option) {
        switch (option) {
            case EXIT -> exit();
            case ADD_DEVICE -> deviceService.add();
            case ADD_CATEGORY -> categoryService.add();
            case ADD_CUSTOMER -> customerService.add();
            case DEL_DEVICE -> deviceService.delete();
            case DEL_CATEGORY -> categoryService.delete();
            case DEL_CUSTOMER -> customerService.delete();
            case RENT_DEVICE -> rentService.rentDevice();
            case RETURN_DEVICE -> rentService.returnDevice();
            default -> System.out.println("Nie ma takiej opcji wybory");
        }
    }

    @SneakyThrows
    private void exit() {
        System.out.println("Zakończenie programu. Bye! Bye!");
        Thread.sleep(1000);
    }

    private OptionMenu choseOption() {
        try {
            OptionMenu optionMenu = OptionMenu.optionById(scanner.nextInt()).orElseThrow();
            scanner.nextLine();
            return optionMenu;
        } catch (NoSuchElementException e) {
            System.out.println("Nie ma takiej opcji wyboru.");
            scanner.nextLine();
        }
        return null;
    }

    private void printMenu() {
        System.out.println("Wybierz opcję:");
        OptionMenu[] values = OptionMenu.values();
        for (int i = 1; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println(OptionMenu.EXIT);
    }

    enum OptionMenu {
        EXIT(0, "Wyjście z programu"),
        RENT_DEVICE(1, "Wypożycz narzędzie"),
        RETURN_DEVICE(2, "Zwróć narzędzie"),
        ADD_DEVICE(3, "Dodaj narzędzie"),
        ADD_CATEGORY(4, "Dodaj kategorię"),
        ADD_CUSTOMER(5, "Dodaj użytkownika"),
        DEL_DEVICE(6, "Usuń narzędzie"),
        DEL_CATEGORY(7, "Usuń kategorię"),
        DEL_CUSTOMER(8, "Usuń użytkownika");

        private final int optionId;
        private final String description;

        OptionMenu(int optionId, String description) {
            this.optionId = optionId;
            this.description = description;
        }

        static Optional<OptionMenu> optionById(int id) {
            return Arrays.stream(OptionMenu.values())
                    .filter(v -> v.optionId == id)
                    .findFirst();
        }

        @Override
        public String toString() {
            return optionId + " - " + description;
        }
    }
}
