package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.Foyer;
import com.example.GestionFoyer.Service.FoyerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foyers")
public class FoyerController {

    private final FoyerService foyerService;

    public FoyerController(FoyerService foyerService) {
        this.foyerService = foyerService;
    }

    @PostMapping("/addFoyer")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @GetMapping
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @GetMapping("/{id}")
    public Foyer getFoyerById(@PathVariable Long id) {
        return foyerService.getFoyerById(id);
    }

    @PutMapping("/{id}")
    public Foyer updateFoyer(@PathVariable Long id, @RequestBody Foyer updatedFoyer) {
        return foyerService.updateFoyer(id, updatedFoyer);
    }

    @DeleteMapping("/{id}")
    public void deleteFoyer(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }
}
