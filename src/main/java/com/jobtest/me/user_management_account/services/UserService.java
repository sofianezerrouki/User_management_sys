package com.jobtest.me.user_management_account.services;

import com.jobtest.me.user_management_account.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;


}
