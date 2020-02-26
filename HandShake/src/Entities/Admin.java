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

   

    public Admin(String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role) {
        super(login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role);
    }
    
     

    public Admin(int userId, String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role, String profil) {
        super(userId, login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role, profil);
    }
    
}
