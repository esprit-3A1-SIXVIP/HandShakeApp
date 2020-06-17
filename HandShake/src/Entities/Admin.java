/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author steph
 */
public class Admin extends User{

   

    public Admin(String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String roles) {
        super(username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles);
    }
    
     

    public Admin(int id, String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String roles, String profil) {
        super(id, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);
    }
    
}
