package com.example.GestionFoyer.Interfaces;

import com.example.GestionFoyer.Entity.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    void deleteReservation(Long id);
}
