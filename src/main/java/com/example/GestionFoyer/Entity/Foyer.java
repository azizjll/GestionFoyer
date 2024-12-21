package com.example.GestionFoyer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idFoyer;

    private String nomFoyer;

    private long capaciteFoyer;


    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    private List<Bloc> blocs;

    @OneToOne
    @JoinColumn(name = "universite_id", referencedColumnName = "idUniversite")
    private Universite universite;
}
