/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Soreilla
 */
public class EvenementSession {
    
    private static EvenementSession instance;
     private int evenementId;
   private String descriptionEvenement;
   private String lieuEvenement;
   private LocalDate dateEvenement;
   private LocalTime heureEvenement;
   private String porteeEvenement;
   private Float prixEvenement;
   
   private EvenementSession() {
    evenementId= getEvenementId();
    descriptionEvenement=getDescriptionEvenement();
    lieuEvenement= getLieuEvenement();
    dateEvenement=getDateEvenement();
    heureEvenement=getHeureEvenement();
    porteeEvenement=getPorteeEvenement();
    prixEvenement=getPrixEvenement();
    
    }

    public EvenementSession(int evenementId, String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement) {
        this.evenementId = evenementId;
        this.descriptionEvenement = descriptionEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.heureEvenement = heureEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
    }
   
     public static EvenementSession getInstace(int evenementId, String descriptionEvenement, String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement, String porteeEvenement, Float prixEvenement) {
        
        if(instance == null) {
            instance = new EvenementSession(evenementId,descriptionEvenement,lieuEvenement,dateEvenement,heureEvenement,porteeEvenement, prixEvenement);
        }
        
        return instance;
    }

    public static EvenementSession getInstance() {
        
        if(instance == null) {
            instance = new EvenementSession();
        }
        
        return instance;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public LocalDate getDateEvenement() {
        return dateEvenement;
    }

    public LocalTime getHeureEvenement() {
        return heureEvenement;
    }

    public String getPorteeEvenement() {
        return porteeEvenement;
    }

    public Float getPrixEvenement() {
        return prixEvenement;
    }
   
    
}
