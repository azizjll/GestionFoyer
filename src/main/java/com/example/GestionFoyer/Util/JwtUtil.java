package com.example.GestionFoyer.Util;

import com.example.GestionFoyer.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "vF2k3/Nq89LpXjwHsk02MkpzU9LtMvjZ3QsP4N29wTk=";

    // Méthode pour générer le token
    public String generateToken(String  email) {
        return Jwts.builder()
                .setSubject(email) // Utiliser l'email comme sujet
                .setIssuedAt(new Date()) // Date d'émission du token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Durée d'expiration (10 heures)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signature avec la clé secrète
                .compact(); // Générer le token compact
    }

    // Extraire l'email à partir du token
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Clé secrète utilisée pour la signature
                .parseClaimsJws(token) // Parser le token
                .getBody()
                .getSubject(); // Extraire le sujet (email dans ce cas)
    }

    // Valider le token en comparant l'email extrait du token avec celui de l'utilisateur
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String email = extractEmail(token); // Extraire l'email du token
            return email.equals(userDetails.getUsername()) && !isTokenExpired(token); // Comparer avec l'email de l'utilisateur et vérifier la date d'expiration
        } catch (Exception e) {
            return false; // La validation échoue en cas d'erreur
        }
    }

    // Vérifier si le token est expiré
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY) // Clé secrète utilisée pour la signature
                .parseClaimsJws(token) // Parser le token
                .getBody()
                .getExpiration(); // Extraire la date d'expiration
        return expiration.before(new Date()); // Vérifier si la date d'expiration est avant la date actuelle
    }
}
