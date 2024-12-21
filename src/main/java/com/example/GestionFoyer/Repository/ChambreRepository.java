package com.example.GestionFoyer.Repository;

import com.example.GestionFoyer.Entity.Chambre;
import com.example.GestionFoyer.Entity.TypeC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByBlocId(Long blocId);
    public List<Chambre> findByTypeC(TypeC typeC);

    List<Chambre> findByDisponibiliteTrue(); // Exemple pour chambres disponibles

}
