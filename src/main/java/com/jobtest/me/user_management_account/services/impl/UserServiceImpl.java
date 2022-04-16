package com.jobtest.me.user_management_account.services.impl;

import com.jobtest.me.user_management_account.exceptions.UsernameExistException;
import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.Role;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.RoleRepository;
import com.jobtest.me.user_management_account.repo.UserRepository;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public User saveUser(User user) {
        //check if user name exist
        User myUser = userRepository.findByUsername(user.getUsername());
        if (myUser!=null){
            throw new UsernameExistException("Username already exist: "+ user.getUsername());
        }
        //chek if email exist
        if (myUser!=null){
            throw new UsernameExistException("Email already exist: "+ user.getEmail());
        }
        //if Role exist we shouldn't resave again
        Authority myAuthority = roleRepository.findByRoleName(Role.ROLE_NEW.name());
        if (myAuthority != null){
            user.addAuthority(myAuthority);
        }else {
            Authority authority = new Authority(Role.ROLE_NEW.name());
            roleRepository.save(authority);
            user.addAuthority(authority);
        }
        return userRepository.save(user);
    }
}
