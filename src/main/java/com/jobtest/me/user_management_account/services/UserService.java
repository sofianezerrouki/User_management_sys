package com.jobtest.me.user_management_account.services;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User updateUser(UserDto userDto);

    List<User> findAll();

    Optional<User> findById(Long idUser);
}
