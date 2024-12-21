package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Bloc;
import com.example.GestionFoyer.Interfaces.IBlocService;
import com.example.GestionFoyer.Repository.BlocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocService implements IBlocService {
    @Autowired
    private  BlocRepository blocRepository;

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public List<Bloc> getBlocsByFoyer(Long foyerId) {
        return blocRepository.findByIdBloc(foyerId);
    }

    @Override
    public Bloc updateBloc(Long id, Bloc updatedBloc) {
        return blocRepository.findById(id).map(bloc -> {
            bloc.setNomBloc(updatedBloc.getNomBloc());
            bloc.setCapaciteBloc(updatedBloc.getCapaciteBloc());
            return blocRepository.save(bloc);
        }).orElseThrow(() -> new RuntimeException("Bloc not found"));
    }

    @Override
    public void deleteBloc(Long id) {
        blocRepository.deleteById(id);
    }
}
