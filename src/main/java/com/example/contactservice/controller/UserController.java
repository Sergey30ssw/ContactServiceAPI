package com.example.contactservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final String[] NAMES = {"Alice", "Bob", "Charlie", "Diana"};

    @GetMapping("/random")
    public String getRandomUser() {
        Random random = new Random();
        return NAMES[random.nextInt(NAMES.length)];
    }
}