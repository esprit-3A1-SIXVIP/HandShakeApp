/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author steph
 */
public  class  Dons {
    
    private int donId;
    private String cibleDon;
    private String typeDon; 
    private Date dateDon = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dat = dateFormat.format(dateDon);
    private int userI;

    public Dons() {
    }

    public Dons(int donId, String typeDon, int userI) {
        this.donId = donId;
        this.typeDon = typeDon;
        this.userI = userI;
    }

    public Dons(int donId, String typeDon, String cibleDon ,int id) {
        this.donId = donId;
        this.typeDon = typeDon;
        this.cibleDon = cibleDon;
        
        this.userI = id;
    }
    
     public Dons( String cibleDon,int id) {
        
        this.cibleDon = cibleDon;
        
        this.userI = id;
    }
     
      public Dons( String cibleDon) {
        
        this.cibleDon = cibleDon;
       
    }
     

    public int getDonId() {
        return donId;
    }

    public void setDonId(int donId) {
        this.donId = donId;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public String getCibleDon() {
        return cibleDon;
    }

    public void setCibleDon(String cibleDon) {
        this.cibleDon = cibleDon;
    }

    public Date getDateDon() {
        return dateDon;
    }

    public void setDateDon(Date dateDon) {
        this.dateDon = dateDon;
    }

    public int getid() {
       
        return userI;
    }

    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.donId;
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
        final Dons other = (Dons) obj;
        if (this.donId != other.donId) {
            return false;
        }
        if (!Objects.equals(this.typeDon, other.typeDon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " idDon = " + donId +", id ="+userI+ ", type = " + typeDon +", Cible = " + cibleDon+ ", Date = "+dateDon;
    }
    
    
    
}
