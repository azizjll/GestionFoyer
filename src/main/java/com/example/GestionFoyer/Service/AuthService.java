package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Repository.UserRepository;
import com.example.GestionFoyer.Util.JwtUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.mailSender = mailSender;
    }

    // Méthode pour obtenir un utilisateur par email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null); // Retourne null si l'utilisateur n'existe pas
    }

    // Méthode pour enregistrer un nouvel utilisateur avec vérification par e-mail
    public void register(User user) {
        // Vérification si l'utilisateur existe déjà
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Utilisateur déjà existant.");
        }

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Générer un token JWT pour l'e-mail de vérification
        String token = jwtUtil.generateToken(user.getEmail());
        user.setToken(token);

        // Initialiser la vérification comme false
        user.setVerified(false);

        // Sauvegarder l'utilisateur
        userRepository.save(user);

        // Envoyer l'email de vérification
        sendVerificationEmail(user.getEmail(), token);
    }

    // Méthode pour envoyer un e-mail de vérification
    private void sendVerificationEmail(String email, String token) {
        String verificationLink = "http://localhost:8075/api/auth/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Vérifiez votre adresse e-mail");
        message.setText("Veuillez cliquer sur le lien suivant pour vérifier votre adresse e-mail : " + verificationLink);

        mailSender.send(message);
    }

    // Méthode pour vérifier un utilisateur via le token
    public String verifyUser(String token) {
        String email = jwtUtil.extractEmail(token);

        // Récupérer l'utilisateur par email
        User user = getUserByEmail(email);

        // Vérifier la validité du token
        if (!jwtUtil.validateToken(token, user)) {
            throw new RuntimeException("Le token est invalide ou expiré.");
        }

        // Mettre à jour le statut de vérification
        user.setVerified(true);
        user.setToken(null); // Invalider le token après vérification
        userRepository.save(user);

        return "Utilisateur vérifié avec succès.";
    }

    // Méthode pour mettre à jour un utilisateur
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Méthode pour charger un utilisateur par son email dans le cadre de l'authentification
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));
        return user; // Retourner l'utilisateur qui implémente UserDetails
    }
}
