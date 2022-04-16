package com.jobtest.me.user_management_account.controllers;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.dto.UserResponse;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("signUp")
    public UserResponse signUp(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
    @PutMapping
    public UserResponse update(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("{idUser}")
    public Optional<UserResponse> findById(@PathVariable Long idUser){
        return userService.findById(idUser);
    }

    @GetMapping("search")
    public List<UserResponse> search(
            @RequestParam(required = false) String usernameOrEmail,
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size){

        return userService.search(usernameOrEmail, PageRequest.of(page,size));
    }

}


