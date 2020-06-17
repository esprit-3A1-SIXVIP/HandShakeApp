/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.UUID;

/**
 *
 * @author amisa
 */
public class Organisation extends User {

    String nomOrganisation;
    String domaine;


    public Organisation(String nomOrganisation, String domaine, String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String profil) {
        super(username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, profil);
        this.nomOrganisation = nomOrganisation;
        this.domaine = domaine;
    }

    public Organisation(String nomOrganisation, String domaine, int id, String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String roles, String profil) {
        super(id, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);
        this.nomOrganisation = nomOrganisation;
        this.domaine = domaine;
    }

    public Organisation(String nomOrganisation, String ville, String domaine, String pays, String email) {
        super(email, ville, pays);
        this.nomOrganisation = nomOrganisation;
        this.domaine = domaine;
    }


    public String getNomOrganisation() {
        return nomOrganisation;
    }

    public void setNomOrganisation(String nomOrganisation) {
        this.nomOrganisation = nomOrganisation;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return "Organisation{" + super.toString() + "nomOrganisation=" + nomOrganisation + ", domaine=" + domaine + '}';
    }

}
