package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Chambre;
import com.example.GestionFoyer.Interfaces.IChambreService;
import com.example.GestionFoyer.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChambreService implements IChambreService {
    @Autowired
    private ChambreRepository chambreRepository;


    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public List<Chambre> getChambresByBloc(Long blocId) {
        return chambreRepository.findByBlocId(blocId);
    }

    @Override
    public Chambre updateChambre(Long id, Chambre updatedChambre) {
        return chambreRepository.findById(id).map(chambre -> {
            chambre.setNumeroChambre(updatedChambre.getNumeroChambre());
            chambre.setTypeC(updatedChambre.getTypeC());
            return chambreRepository.save(chambre);
        }).orElseThrow(() -> new RuntimeException("Chambre not found"));
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

}
