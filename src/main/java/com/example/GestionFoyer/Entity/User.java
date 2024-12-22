package com.example.GestionFoyer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    private String nomUser;
    private String prenomUser;

    private long CIN;

    private String ecole;

    private Date dateNaissance;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    private boolean isVerified = false;

    // Implémentation des méthodes de l'interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne les rôles de l'utilisateur sous forme de GrantedAuthority
        return List.of(() -> role.name()); // Par exemple, le rôle peut être "USER", "ADMIN"
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implémentez selon votre logique, par exemple vérifier si le compte est expiré
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implémentez selon votre logique, par exemple vérifier si le compte est verrouillé
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implémentez selon votre logique, par exemple vérifier si les informations d'identification ont expiré
    }

    @Override
    public boolean isEnabled() {
        return true; // Implémentez selon votre logique, par exemple vérifier si l'utilisateur est activé
    }
}
