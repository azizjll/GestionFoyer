package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.Chambre;

import java.util.List;

public interface IChambreService {
    Chambre addChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    List<Chambre> getChambresByBloc(Long blocId);
    Chambre updateChambre(Long id, Chambre updatedChambre);
    void deleteChambre(Long id);
}
