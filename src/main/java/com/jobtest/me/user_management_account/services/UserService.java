package com.jobtest.me.user_management_account.services;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.dto.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse saveUser(UserDto userDto) throws RuntimeException;

    UserResponse updateUser(UserDto userDto);

    List<UserResponse> findAll();

    Optional<UserResponse> findById(Long idUser);

    List<UserResponse> search(String keyword, Pageable pageable);
}
