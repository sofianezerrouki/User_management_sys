package com.jobtest.me.user_management_account.services.impl;

import com.jobtest.me.user_management_account.dto.UserDto;
import com.jobtest.me.user_management_account.exceptions.EmailExistException;
import com.jobtest.me.user_management_account.exceptions.UserNotFoundException;
import com.jobtest.me.user_management_account.exceptions.UsernameExistException;
import com.jobtest.me.user_management_account.models.Authority;
import com.jobtest.me.user_management_account.models.Role;
import com.jobtest.me.user_management_account.models.User;
import com.jobtest.me.user_management_account.repo.RoleRepository;
import com.jobtest.me.user_management_account.repo.UserRepository;
import com.jobtest.me.user_management_account.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public User saveUser(User user) throws RuntimeException{
        //check if user name exist
        User myUser = userRepository.findByUsername(user.getUsername());
        if (myUser!=null){
            throw new UsernameExistException("Username already exist: "+ user.getUsername());
        }
        //chek if email exist
        if (myUser!=null){
            throw new EmailExistException("Email already exist: "+ user.getEmail());
        }
        saveOrUpdatepdateUserRoles(user, Arrays.asList(Role.ROLE_NEW.name()));
        return userRepository.save(user);
    }

    private void saveOrUpdatepdateUserRoles(User user, List<String> roles) {
        Set<Authority> authorities = new HashSet<>();
        for(String roleString:roles){
            //if Role exist we shouldn't resave again
            Authority myAuthority = roleRepository.findByRoleName(roleString);
            if (myAuthority != null){
                authorities.add(myAuthority);
            }else {
                myAuthority = new Authority(roleString);
                roleRepository.save(myAuthority);
                authorities.add(myAuthority);
            }
        }
        user.setRoles(authorities);
    }

    @Override
    public User updateUser(UserDto userDto) {
        Optional<User> myUser = userRepository.findById(userDto.getId());
        if (myUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        User user = myUser.get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        saveOrUpdatepdateUserRoles(user,userDto.getRoles());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public List<User> search(String keyword, Pageable pageable) {
        return userRepository.search(keyword);
    }
}
