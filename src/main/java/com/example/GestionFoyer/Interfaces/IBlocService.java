package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.Bloc;

import java.util.List;

public interface IBlocService {
    Bloc addBloc(Bloc bloc);
    List<Bloc> getAllBlocs();
    List<Bloc> getBlocsByFoyer(Long foyerId);
    Bloc updateBloc(Long id, Bloc updatedBloc);
    void deleteBloc(Long id);
}
