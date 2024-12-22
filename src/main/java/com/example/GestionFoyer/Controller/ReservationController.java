package com.example.GestionFoyer.Controller;

import com.example.GestionFoyer.Entity.Reservation;
import com.example.GestionFoyer.Service.PdfGeneratorService;
import com.example.GestionFoyer.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private  ReservationService reservationService;
    @Autowired
    private PdfGeneratorService pdfGeneratorService;



    @PostMapping("/createReservation")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Utilisateur authentifié : " + authenticatedUser);

        // Appeler le service pour créer la réservation
        Reservation createdReservation = reservationService.createReservation(reservation);

        try {
            // Générer le fichier PDF
            byte[] pdfContent = pdfGeneratorService.generateReservationPdf(createdReservation);

            // Créer une réponse contenant le PDF
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=reservation.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfContent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de la génération du PDF");
        }
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
