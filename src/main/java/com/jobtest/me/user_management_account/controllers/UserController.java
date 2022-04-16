package com.jobtest.me.user_management_account.controllers;

import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private UserRepository  userRepository;
    @GetMapping("test")
    public String test(){
        return  "Hello job test :) ";
    }
    @PostMapping
    public void storeUser(@RequestBody User user){
        Authority authority = new Authority();
        roleRepository.save("")
        userRepository.save(user);
    }
}
