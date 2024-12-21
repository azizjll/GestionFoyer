package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.Universite;
import com.example.GestionFoyer.Service.UniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universites")
public class UniversiteController {

    @Autowired
    private UniversiteService universiteService;

    @PostMapping("/createUniversite")
    public ResponseEntity<Universite> createUniversite(@RequestBody Universite universite) {
        return ResponseEntity.ok(universiteService.createUniversite(universite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Universite> updateUniversite(@PathVariable Long id, @RequestBody Universite updatedUniversite) {
        return ResponseEntity.ok(universiteService.updateUniversite(id, updatedUniversite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversite(@PathVariable Long id) {
        universiteService.deleteUniversite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universite> getUniversiteById(@PathVariable Long id) {
        return ResponseEntity.ok(universiteService.getUniversiteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Universite>> getAllUniversites() {
        return ResponseEntity.ok(universiteService.getAllUniversites());
    }
}
