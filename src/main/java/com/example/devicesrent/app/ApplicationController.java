package com.example.devicesrent.app;

import com.example.devicesrent.data.category.CategoryService;
import com.example.devicesrent.data.customer.CustomerService;
import com.example.devicesrent.data.device.DeviceService;
import com.example.devicesrent.data.rent.RentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationController {
    private final Scanner scanner;
    private final DeviceService deviceService;
    private final CategoryService categoryService;
    private final CustomerService customerService;
    private final RentService rentService;

    public ApplicationController(Scanner scanner,
                                 DeviceService deviceService,
                                 CategoryService categoryService,
                                 CustomerService customerService,
                                 RentService rentService) {
        this.scanner = scanner;
        this.deviceService = deviceService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    public void mainLoop() {
        System.out.println("WYPOŻYCZALNIA NARZĘDZI");
        OptionMenu option;
        do {
            printMenu();
            option = choseOption();
            executeOption(option);
        } while (option != OptionMenu.EXIT);
    }

    private void executeOption(OptionMenu option) {
        switch (option) {
            case EXIT -> exit();
            case ADD_DEVICE -> deviceService.addNewDevice();
            case ADD_CATEGORY -> categoryService.addNewCategory();
            case ADD_CUSTOMER -> customerService.addNewCustomer();
            case DEL_DEVICE -> deviceService.deleteDevice();
            case DEL_CATEGORY -> categoryService.deleteCategory();
            case DEL_CUSTOMER -> customerService.deleteCustomer();
            case RENT_DEVICE -> rentService.rentDevice();
            case RETURN_DEVICE -> rentService.returnDevice();
            case FIND_CATEGORY -> categoryService.findCategory();
            case FIND_DEVICE -> deviceService.findDevice();
            case FIND_CUSTOMER -> customerService.findCustomerByPesel();
        }
    }

    @SneakyThrows
    private void exit() {
        System.out.println("Zakończenie programu. Bye! Bye!");
        Thread.sleep(1000);
    }

    private OptionMenu choseOption() {
        Optional<OptionMenu> optionMenu = Optional.empty();
        while (optionMenu.isEmpty()) {
            try{
                optionMenu = OptionMenu.optionById(scanner.nextInt());
                if(optionMenu.isPresent()) {
                    scanner.nextLine();
                    return optionMenu.get();
                } else {
                    throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                scanner.nextLine();
                System.out.println("Nie ma takiej opcji wyboru!");
            }
        }
        return optionMenu.get();
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
        DEL_CUSTOMER(8, "Usuń użytkownika"),
        FIND_CATEGORY(9,"Wyszukaj kategorię narzędzi"),
        FIND_DEVICE(10, "Znajdź narzędzie"),
        FIND_CUSTOMER(11, "Znajdź użytkownika po numerze PESEL");

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
