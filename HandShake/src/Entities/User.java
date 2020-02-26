/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author steph
 */
public class User {
    
   int userId;
   String login;
   String password;
   String nomUser;
   String prenomUser ;
   String email ;
   int telephone ;
   String profil;
   String ville ;
   String rue ;
   String pays ;
   String role ;
   boolean accesShakeHub;
   
    public User(int userId, String login, String password, String email, String role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public User(String email, String ville, String pays) {
        this.email = email;
        this.ville = ville;
        this.pays = pays;
    }
    public User( int userId,String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role) {
        this.userId = userId;
        this.login = login;
        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.role = role;
    }
 public User(String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays,String profil) {
        this.userId=(int) (Math.random()*(400000-20000));
       
        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
  
        this.profil=profil;
    }

    public User(int userId, String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role, String profil) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.role = role;
        this.profil = profil;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

   

  

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "\nUser{"+",login=" + login + ", password=" + password + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", email=" + email + ", telephone=" + telephone + ", ville=" + ville + ", rue=" + rue + ", pays=" + pays + ", role=" + role + '}';
    }

    public boolean isAccesShakeHub() {
        return accesShakeHub;
    }

    public void setAccesShakeHub(boolean accesShakeHub) {
        this.accesShakeHub = accesShakeHub;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }
   
    
    
}

