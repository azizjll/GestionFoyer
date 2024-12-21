package com.example.GestionFoyer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idUser;

    private String nomUser;
    private String prenomUser;

    private  long CIN;

    private String ecole;

    private Date dateNaissance;
    @Column(unique = true, nullable = false)
    private String email;


    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;


}
