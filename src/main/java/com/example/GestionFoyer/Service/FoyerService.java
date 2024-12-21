package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Foyer;
import com.example.GestionFoyer.Interfaces.IFoyerService;
import com.example.GestionFoyer.Repository.FoyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoyerService implements IFoyerService {

    @Autowired
    private  FoyerRepository foyerRepository;


    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer getFoyerById(Long id) {
        return foyerRepository.findById(id).orElseThrow(() -> new RuntimeException("Foyer not found"));
    }

    @Override
    public Foyer updateFoyer(Long id, Foyer updatedFoyer) {
        return foyerRepository.findById(id).map(foyer -> {
            foyer.setNomFoyer(updatedFoyer.getNomFoyer()); // Mise à jour du nom
            foyer.setCapaciteFoyer(updatedFoyer.getCapaciteFoyer()); // Mise à jour de la capacité
            foyer.setUniversite(updatedFoyer.getUniversite()); // Mise à jour de l'université
            return foyerRepository.save(foyer);
        }).orElseThrow(() -> new RuntimeException("Foyer not found"));
    }

    @Override
    public void deleteFoyer(Long id) {
        foyerRepository.deleteById(id);
    }



}
