package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Service.AuthService;
import com.example.GestionFoyer.Util.JwtUtil;
import org.springframework.http.HttpStatus;
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

        if (existingUser == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // Vérifier si l'utilisateur a confirmé son email
        if (!existingUser.isVerified()) {
            return ResponseEntity.status(403).body("Email not verified. Please check your inbox to verify your email.");
        }

        // Vérifier le mot de passe
        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Générer le token JWT
            String token = jwtUtil.generateToken(existingUser.getEmail());

            // Enregistrer le token dans l'utilisateur
            existingUser.setToken(token);
            authService.updateUser(existingUser);

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }







    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            authService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur enregistré avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'enregistrement.");
        }
    }



    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        try {
            // Extraire l'email depuis le token
            String email = jwtUtil.extractEmail(token);

            // Récupérer l'utilisateur par email
            User user = authService.getUserByEmail(email);

            if (user == null) {
                return ResponseEntity.status(404).body("Utilisateur non trouvé.");
            }

            // Marquer l'utilisateur comme vérifié
            user.setVerified(true);
            authService.updateUser(user);

            return ResponseEntity.ok("Votre email a été vérifié avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Token invalide ou expiré.");
        }
    }

}
