/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author USER
 */
public class Aide {
    private int aideID;
    private String descriptionAide;
    private String localisationAide;
    private String categorieAide;

    public Aide() {
    }

    public Aide(int aideID) {
        this.aideID = aideID;
    }

    public Aide( int aideID,String descriptionAide, String localisationAide, String categorieAide) {
        
        this.aideID=aideID ;
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
    }
        public Aide(String descriptionAide, String localisationAide, String categorieAide) {
        
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
    }
    

    public int getAideID() {
        return aideID;
    }

    public String getDescriptionAide() {
        return descriptionAide;
    }

    public String getLocalisationAide() {
        return localisationAide;
    }

    public String getCategorieAide() {
        return categorieAide;
    }


    public void setDescriptionAide(String descriptionAide) {
        this.descriptionAide = descriptionAide;
    }

    public void setLocalisationAide(String localisationAide) {
        this.localisationAide = localisationAide;
    }

    public void setCategorieAide(String categorieAide) {
        this.categorieAide = categorieAide;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.aideID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aide other = (Aide) obj;
        return this.aideID == other.aideID;
    }

    @Override
    public String toString() {
        return "Aide{" + "aideID=" + aideID + ", descriptionAide=" + descriptionAide + ", localisationAide=" + localisationAide + ", categorieAide=" + categorieAide + '}';
    }
    
    
    
}
