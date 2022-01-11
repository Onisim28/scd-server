package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("getStandardUsers")
    public List<User> getStandardUsers() {
        return userService.getStandardUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        System.out.println("A ajuns aici!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return userService.create(user);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/login")
    public User LoginUser(@RequestBody User user) {
        return userService.LoginUser(user);
    }

}

