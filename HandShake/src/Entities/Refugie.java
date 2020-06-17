
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
public class Refugie extends Beneficiaire {
    private String nationalite;

    public Refugie(String nationalite, int beneficiaireId, int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email, java.sql.Date dateNaissance, String ville, int telephone, String adresseGPS, String roles) {
        super(beneficiaireId, aideId, nomBeneficiaire, prenomBeneficiaire, email, dateNaissance, ville, telephone, adresseGPS, roles);
        this.nationalite = nationalite;
    }

    public Refugie(String nationalite, int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email, java.sql.Date dateNaissance, String ville, int telephone, String adresseGPS, String roles) {
        super(aideId, nomBeneficiaire, prenomBeneficiaire, email, dateNaissance, ville, telephone, adresseGPS, roles);
        this.nationalite = nationalite;
    }

    public Refugie(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public String toString() {
        return "Refugie{" + "nationalite=" + nationalite + '}';
    }

 
}

