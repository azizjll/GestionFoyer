package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Service.AuthService;
import com.example.GestionFoyer.Util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Récupérer l'utilisateur par email
        User existingUser = authService.getUserByEmail(user.getEmail());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Générer le token JWT
            String token = jwtUtil.generateToken(existingUser);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Vérifier si l'utilisateur existe déjà
        if (authService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(400).body("Email already taken");
        }

        // Vérifier que le mot de passe n'est pas nul ou vide
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body("Password cannot be null or empty");
        }

        // Encoder le mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Enregistrer l'utilisateur dans la base de données
        authService.saveUser(user);

        return ResponseEntity.status(201).body("User registered successfully");
    }


}
