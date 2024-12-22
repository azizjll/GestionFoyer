package com.example.GestionFoyer.Service;

import com.example.GestionFoyer.Entity.Reservation;
import com.example.GestionFoyer.Entity.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public byte[] generateReservationPdf(Reservation reservation) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Initialisation du PDF
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Contenu du PDF
        document.add(new Paragraph("Détails de la réservation"));
        document.add(new Paragraph("ID de la réservation : " + reservation.getIdReservation()));
        document.add(new Paragraph("Année universitaire : " + reservation.getAnneeUniversitaire()));
        document.add(new Paragraph("Chambre réservée : " +
                (reservation.getChambre() != null ? reservation.getChambre().getIdChambre() : "Non spécifiée")));
        document.add(new Paragraph("Est valide : " + reservation.isEstValid()));

        if (reservation.getUsers() != null && !reservation.getUsers().isEmpty()) {
            document.add(new Paragraph("Utilisateurs associés :"));
            for (User user : reservation.getUsers()) {
                document.add(new Paragraph("- " + user.getNomUser() + " " + user.getPrenomUser()));
            }
        }

        // Fermer le document
        document.close();

        return outputStream.toByteArray();
    }
}
