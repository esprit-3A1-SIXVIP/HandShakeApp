/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.itextpdf.text.Image;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Soreilla
 */
public class Evenement {
   private int evenementId;
   private String descriptionEvenement;
   private String lieuEvenement;
   private LocalDate dateEvenement;
   private LocalTime heureEvenement;
   private String porteeEvenement;
   private Float prixEvenement;
   private int id;
   private String image;

    public Evenement() {
    }

    public Evenement(String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement) {
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
    }

    public Evenement(int evenementId) {
        this.evenementId = evenementId;
    }

    public Evenement(int evenementId, String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement, int id, String image) {
        this.evenementId = evenementId;
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.id = id;
        this.image = image;
    }
    

    public Evenement(int evenementId, String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement) {
        this.evenementId = evenementId;
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
    }
     public Evenement(String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement,int evenementId) {
      
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
          this.evenementId = evenementId;
    }

    public Evenement(int evenementId, String descriptionEvenement) {
        this.evenementId = evenementId;
        this.descriptionEvenement = descriptionEvenement;
    }

    public Evenement(String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement, String image) {
      
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.image = image;
    }

    public Evenement(int evenementId, String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement, String image) {
        this.evenementId = evenementId;
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.image = image;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public LocalDate getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(LocalDate dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public LocalTime getHeureEvenement() {
        return heureEvenement;
    }

    public void setHeureEvenement(LocalTime heureEvenement) {
        this.heureEvenement = heureEvenement;
    }

    public String getPorteeEvenement() {
        return porteeEvenement;
    }

    public void setPorteeEvenement(String porteeEvenement) {
        this.porteeEvenement = porteeEvenement;
    }

    public Float getPrixEvenement() {
        return prixEvenement;
    }

    public void setPrixEvenement(Float prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "evenementId=" + evenementId + ", descriptionEvenement=" + descriptionEvenement + ", lieuEvenement=" + lieuEvenement + ", dateEvenement=" + dateEvenement + ", heureEvenement=" + heureEvenement + ", porteeEvenement=" + porteeEvenement + ", prixEvenement=" + prixEvenement + '}';
    }
   
   
   
    
    
}
