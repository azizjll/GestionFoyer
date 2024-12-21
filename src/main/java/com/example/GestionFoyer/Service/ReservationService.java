package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Chambre;
import com.example.GestionFoyer.Entity.Reservation;
import com.example.GestionFoyer.Entity.User;
import com.example.GestionFoyer.Interfaces.IReservationService;
import com.example.GestionFoyer.Repository.ChambreRepository;
import com.example.GestionFoyer.Repository.ReservationRepository;
import com.example.GestionFoyer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        // Vérifiez si la chambre existe
        Chambre chambre = chambreRepository.findById(reservation.getChambre().getIdChambre())
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        // Vérifiez si les utilisateurs existent
        for (User user : reservation.getUsers()) {
            User existingUser = userRepository.findById(user.getIdUser())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
        // Sauvegardez la réservation
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
