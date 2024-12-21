package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Méthode pour enregistrer un nouvel utilisateur
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
