package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    List<User> getAllUsers();
    List<User> searchUsers(String keyword);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}
