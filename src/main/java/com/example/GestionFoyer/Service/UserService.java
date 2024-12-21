package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Interfaces.IUserService;
import com.example.GestionFoyer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userRepository.findByNomUserContaining(keyword);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setNomUser(updatedUser.getNomUser());
            user.setPrenomUser(updatedUser.getPrenomUser());
            user.setCIN(updatedUser.getCIN());
            user.setEcole(updatedUser.getEcole());
            user.setDateNaissance(updatedUser.getDateNaissance());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
