package com.jobtest.me.user_management_account.controllers;

import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.Role;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.RoleRepository;
import com.jobtest.me.user_management_account.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private UserRepository  userRepository;
    private RoleRepository roleRepository;
    @GetMapping("test")
    public String test(){
        return  "Hello job test :) ";
    }
    @PostMapping
    public void storeUser(@RequestBody User user){
        //check if user name ixist
        //chek if email exist
        Authority authority = new Authority(Role.ROLE_NEW.name());
        roleRepository.save(authority);
        user.addAuthority(authority);
        userRepository.save(user);
    }
}

//        SELECT * FROM AUTHORITY ;
//        SELECT * FROM USER;
//        SELECT * FROM USERS_ROLES ;
