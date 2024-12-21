package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.Universite;

import java.util.List;

public interface IUniversiteService {

    Universite createUniversite(Universite universite);

    Universite updateUniversite(Long id, Universite updatedUniversite);

    void deleteUniversite(Long id);

    Universite getUniversiteById(Long id);

    List<Universite> getAllUniversites();


}
