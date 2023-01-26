package com.backend.controller;

import com.backend.model.UsersModel;
import com.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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
    public int saveUser(@RequestBody UsersModel usersModel)
    {
        if((usersService.save(usersModel))==0){
            return 0;
        }else{
            return 1;
        }
    }
    @PostMapping("/login")
    public int loginUser(@RequestBody UsersModel usersModel)
    {
        if(usersService.authenticate(usersModel.getEmail(),usersModel.getPassword())==null)
        {
            return 0;
        }else {
            return (usersService.authenticate(usersModel.getEmail(), usersModel.getPassword())).getId();
        }
    }
}
