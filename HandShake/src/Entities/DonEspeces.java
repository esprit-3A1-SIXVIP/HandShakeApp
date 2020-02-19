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
public class DonEspeces extends Dons{
    
    private int montantDon;
    

    public DonEspeces() {
    }

    public DonEspeces(int montantDon, String cibleDon,int id) {
        super(cibleDon, id);
        this.montantDon = montantDon;
    }

    public DonEspeces(int donId,int montantDon,  String typeDon, String cibleDon,int id,Date date) {
        super(donId,typeDon,cibleDon, id);
        this.montantDon = montantDon;
    }

    
   

    public DonEspeces(int montantDon) {
        this.montantDon = montantDon;
       
    }

    public int getMontantDon() {
        return montantDon;
    }

    public void setMontantDon(int montantDon) {
        this.montantDon = montantDon;
    }

   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.montantDon;
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
        final DonEspeces other = (DonEspeces) obj;
        if (this.montantDon != other.montantDon) {
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "DonEspeces{"+super.toString() + ",  montantDon = " + montantDon + '}';
    }

    
    
    
    
}
