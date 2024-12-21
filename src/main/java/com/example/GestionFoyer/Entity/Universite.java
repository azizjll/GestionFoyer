package com.example.GestionFoyer.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idUniversite;

    private String nomUniversite;

    private String adress;

    @OneToOne(mappedBy = "universite", cascade = CascadeType.ALL)
    @JsonIgnore
    private Foyer foyer;
}
