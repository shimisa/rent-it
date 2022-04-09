package com.example.rentit.userservice.service;

import com.example.rentit.userservice.domain.Role;
import com.example.rentit.userservice.domain.RoleName;
import com.example.rentit.userservice.domain.User;

import java.util.List;

/**
 * User service to store and get users in the database
 *
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */

public interface UserService {
    User saveUser(User user);
    //Role saveRole(Role role);
    void addRoleToUser(String username, RoleName roleName);
    User getUser(String username);
    List<User> getUsers(int page);
    String signUpUser(User user);
    void enableUser(String email);
}
