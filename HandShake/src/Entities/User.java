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
    
   int id;
   String username;
   String password;
   String nomUser;
   String prenomUser ;
   String email ;
   int telephone ;
   String profil;
   String ville ;
   String rue ;
   String pays ;
   String roles ;
   int accesShakeHub;
   String nomOrganisation;
  String domaine;

    public User(String email, String nomOrganisation, String domaine, String ville, String pays) {
        this.email = email;
        this.nomOrganisation = nomOrganisation;
        this.domaine = domaine;
        this.ville = ville;
        this.pays = pays;
    }
   
    public User(int id, String username, String password, String email, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }


    public User(int id, String username, String password, String email, String roles, int accesShakeHub) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.accesShakeHub = accesShakeHub;
    }
    
    public User(String email, String ville, String pays) {

        this.email = email;
        this.ville = ville;
        this.pays = pays;
    }
    public User( int id,String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String roles) {
        this.id = id;
        this.username = username;
        this.username = username;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.roles = roles;
    }
 public User(String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays,String profil) {
        this.id=(int) (Math.random()*(400000-20000));
       
        this.username = username;
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

    public User(int id, String username, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String roles, String profil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.roles = roles;
        this.profil = profil;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

   

  

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public int getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
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

    public String getroles() {
        return roles;
    }

    public void setroles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "\nUser{"+",username=" + username + ", password=" + password + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", email=" + email + ", telephone=" + telephone + ", ville=" + ville + ", rue=" + rue + ", pays=" + pays + ", roles=" + roles + '}';
    }

    public int isAccesShakeHub() {
        return accesShakeHub;
    }

    public void setAccesShakeHub(int accesShakeHub) {
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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }
   
    
    
}
