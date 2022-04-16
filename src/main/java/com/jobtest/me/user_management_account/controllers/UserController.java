package com.jobtest.me.user_management_account.controllers;

import com.jobtest.me.user_management_account.exceptions.UsernameExistException;
import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.Role;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.RoleRepository;
import com.jobtest.me.user_management_account.repo.UserRepository;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}

//        SELECT * FROM AUTHORITY ;
//        SELECT * FROM USER;
//        SELECT * FROM USERS_ROLES ;
