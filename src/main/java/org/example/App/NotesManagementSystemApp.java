package org.example.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotesManagementSystemApp {
    public static void main(String[] args) {
        SpringApplication.run(NotesManagementSystemApp.class, args);
    }
}