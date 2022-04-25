package com.example.devicesrent;

import com.example.devicesrent.app.ApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class DevicesRentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicesRentApplication.class, args);


        ApplicationController applicationController = context.getBean(ApplicationController.class);
        applicationController.mainLoop();

        SpringApplication.exit(context);
    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }


}
