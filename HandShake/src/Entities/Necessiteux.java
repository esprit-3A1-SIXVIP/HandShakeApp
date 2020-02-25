
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author USER
 */
public class Necessiteux extends Beneficiaire{
    private String besoin;

    public Necessiteux(String besoin, int beneficiaireId, int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email, java.sql.Date dateNaissance, String ville, int telephone, String adresseGPS, String role) {
        super(beneficiaireId, aideId, nomBeneficiaire, prenomBeneficiaire, email, dateNaissance, ville, telephone, adresseGPS, role);
        this.besoin = besoin;
    }

    public Necessiteux(String besoin, int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email, java.sql.Date dateNaissance, String ville, int telephone, String adresseGPS, String role) {
        super(aideId, nomBeneficiaire, prenomBeneficiaire, email, dateNaissance, ville, telephone, adresseGPS, role);
        this.besoin = besoin;
    }

    public Necessiteux(String besoin) {
        this.besoin = besoin;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    @Override
    public String toString() {
        return "Necessiteux{" + "besoin=" + besoin + '}';
    }

    
    
}

