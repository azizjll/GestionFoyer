package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.Bloc;
import com.example.GestionFoyer.Service.BlocService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocs")
public class BlocController {

    private final BlocService blocService;

    public BlocController(BlocService blocService) {
        this.blocService = blocService;
    }

    @PostMapping
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @GetMapping
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/foyer/{foyerId}")
    public List<Bloc> getBlocsByFoyer(@PathVariable Long foyerId) {
        return blocService.getBlocsByFoyer(foyerId);
    }

    @PutMapping("/{id}")
    public Bloc updateBloc(@PathVariable Long id, @RequestBody Bloc updatedBloc) {
        return blocService.updateBloc(id, updatedBloc);
    }

    @DeleteMapping("/{id}")
    public void deleteBloc(@PathVariable Long id) {
        blocService.deleteBloc(id);
    }
}
