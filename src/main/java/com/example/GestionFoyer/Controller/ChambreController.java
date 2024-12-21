package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.Chambre;
import com.example.GestionFoyer.Service.ChambreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

    private final ChambreService chambreService;

    public ChambreController(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    @PostMapping
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/bloc/{blocId}")
    public List<Chambre> getChambresByBloc(@PathVariable Long blocId) {
        return chambreService.getChambresByBloc(blocId);
    }

    @PutMapping("/{id}")
    public Chambre updateChambre(@PathVariable Long id, @RequestBody Chambre updatedChambre) {
        return chambreService.updateChambre(id, updatedChambre);
    }

    @DeleteMapping("/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }
}
