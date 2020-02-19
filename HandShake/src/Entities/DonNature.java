/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author steph
 */
public class DonNature extends Dons{
    
    private String libelleDonNature;
    private String categorieDonNature;
    private int quantiteDonNature;

    public DonNature() {
    }

    public DonNature(String libelleDonNature, String categorieDonNature, int quantiteDonNature, int donId, String typeDon, String cibleDon ,Date date,int id) {
        super(donId,typeDon,cibleDon,id);
        this.libelleDonNature = libelleDonNature;
        this.categorieDonNature = categorieDonNature;
        this.quantiteDonNature = quantiteDonNature;
        
    }

    public DonNature(String cibleDon,String libelleDonNature, String categorieDonNature, int quantiteDonNature,int idU) {
        super(cibleDon, idU);
        this.libelleDonNature = libelleDonNature;
        this.categorieDonNature = categorieDonNature;
        this.quantiteDonNature = quantiteDonNature;
    }
    
     public DonNature(String cibleDon,String libelleDonNature, String categorieDonNature, int quantiteDonNature) {
        super(cibleDon);
        this.libelleDonNature = libelleDonNature;
        this.categorieDonNature = categorieDonNature;
        this.quantiteDonNature = quantiteDonNature;
    }

    public String getLibelleDonNature() {
        return libelleDonNature;
    }

    public void setLibelleDonNature(String libelleDonNature) {
        this.libelleDonNature = libelleDonNature;
    }

    public String getCategorieDonNature() {
        return categorieDonNature;
    }

    public void setCategorieDonNature(String categorieDonNature) {
        this.categorieDonNature = categorieDonNature;
    }

    public int getQuantiteDonNature() {
        return quantiteDonNature;
    }

    public void setQuantiteDonNature(int quantiteDonNature) {
        this.quantiteDonNature = quantiteDonNature;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.quantiteDonNature;
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
        final DonNature other = (DonNature) obj;
        if (!Objects.equals(this.libelleDonNature, other.libelleDonNature)) {
            return false;
        }
        if (!Objects.equals(this.categorieDonNature, other.categorieDonNature)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DonNature{"+super.toString() + ",  libelleDonNature = " + libelleDonNature + ", categorieDonNature = " + categorieDonNature + ", quantiteDonNature = " + quantiteDonNature + '}';
    }

    
    
    
}
