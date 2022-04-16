package com.jobtest.me.user_management_account.controllers;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("test")
    public String test(){
        return  "Hello job test :) ";
    }

    @PostMapping
    public User register(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PutMapping
    public User update(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @GetMapping("{idUser}")
    public Optional<User> findAll(@PathVariable Long idUser){
        return userService.findById(idUser);
    }
}

//        SELECT * FROM AUTHORITY ;
//        SELECT * FROM USER;
//        SELECT * FROM USERS_ROLES ;
