package com.example.GestionFoyer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idReservation;

    private LocalDate anneeUniversitaire;

    private boolean estValid;

    @ManyToMany
    @JoinTable(
            name = "user_reservation",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
}
