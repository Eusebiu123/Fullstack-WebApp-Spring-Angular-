package com.backend.controller;

import com.backend.model.UsersModel;
import com.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(path="api/v1")
@RestController
public class UsersController {

    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/getAll")
    public Iterable<UsersModel>getUsers()
    {
        return usersService.listAll();
    }
    @PostMapping("/register")
    public String saveUser(@RequestBody UsersModel usersModel)
    {
        return (usersService.save(usersModel));
    }
}
