package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Universite;
import com.example.GestionFoyer.Interfaces.IUniversiteService;
import com.example.GestionFoyer.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Override
    public Universite createUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }
    @Override
    public Universite updateUniversite(Long id, Universite updatedUniversite) {
        return universiteRepository.findById(id).map(universite -> {
            universite.setNomUniversite(updatedUniversite.getNomUniversite());
            universite.setAdress(updatedUniversite.getAdress());
            return universiteRepository.save(universite);
        }).orElseThrow(() -> new RuntimeException("Universite not found"));
    }

    @Override
    public void deleteUniversite(Long id) {
        if (!universiteRepository.existsById(id)) {
            throw new RuntimeException("Universite not found");
        }
        universiteRepository.deleteById(id);
    }

    @Override
    public Universite getUniversiteById(Long id) {
        return universiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Universite not found"));
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }



}
