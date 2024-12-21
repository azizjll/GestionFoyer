package com.example.GestionFoyer.Repository;

import com.example.GestionFoyer.Entity.Bloc;
import com.example.GestionFoyer.Entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    @Query("SELECT b FROM Bloc b WHERE b.id = :idBloc")
    List<Bloc> findByIdBloc(@Param("idBloc") Long idBloc);
}
