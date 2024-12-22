package com.example.GestionFoyer.Repository;

import com.example.GestionFoyer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNomUserContaining(String nom);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}