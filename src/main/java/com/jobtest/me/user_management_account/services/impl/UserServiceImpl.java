package com.jobtest.me.user_management_account.services.impl;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.dto.UserResponse;
import com.jobtest.me.user_management_account.exceptions.UserNotFoundException;
import com.jobtest.me.user_management_account.exceptions.UsernameExistException;
import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.Role;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.RoleRepository;
import com.jobtest.me.user_management_account.repo.UserRepository;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveUser(UserDto userDto) throws UsernameExistException {
        //check if user name exist
        User myUser = userRepository.findByUsername(userDto.getUsername());
        if (myUser != null) {
            throw new UsernameExistException("Username already exist: " + userDto.getUsername());
        }
        myUser = new User();
        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        myUser.setEmail(userDto.getEmail());
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));

        saveOrUpdatepdateUserRoles(myUser, Arrays.asList(Role.ROLE_NEW.name()));
        User savedUser = userRepository.save(myUser);
        return new UserResponse(savedUser);
    }

    private void saveOrUpdatepdateUserRoles(User user, List<String> roles) {
        Set<Authority> authorities = new HashSet<>();
        for (String roleString : roles) {
            //if Role exist we shouldn't resave again
            Authority myAuthority = roleRepository.findByRoleName(roleString);
            if (myAuthority != null) {
                authorities.add(myAuthority);
            } else {
                myAuthority = new Authority(roleString);
                roleRepository.save(myAuthority);
                authorities.add(myAuthority);
            }
        }
        user.setRoles(authorities);
    }

    @Override
    public UserResponse updateUser(UserDto userDto) {
        Optional<User> myUser = userRepository.findById(userDto.getId());
        if (myUser.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = myUser.get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        saveOrUpdatepdateUserRoles(user, userDto.getRoles());

        User updatedUser = userRepository.save(user);
        return new UserResponse(updatedUser);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> findById(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()){
            return Optional.of(new UserResponse(user.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<UserResponse> search(String keyword, Pageable pageable) {
        List<User> users = userRepository.search(keyword);
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
