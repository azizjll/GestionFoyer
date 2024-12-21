package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer addFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long id);
    Foyer updateFoyer(Long id, Foyer updatedFoyer);
    void deleteFoyer(Long id);
}
