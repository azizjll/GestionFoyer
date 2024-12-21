package com.example.GestionFoyer.Repository;

import com.example.GestionFoyer.Entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    // Méthodes personnalisées si nécessaires, exemple :
    List<Foyer> findByNomFoyerContaining(String nomFoyer);;
}