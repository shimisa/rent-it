package com.example.rentit.userservice.service;

import com.example.rentit.userservice.domain.Role;
import com.example.rentit.userservice.domain.User;

import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
