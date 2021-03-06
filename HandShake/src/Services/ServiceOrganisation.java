/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Organisation;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

/**
 *
 * @author amisa
 */
public class ServiceOrganisation {

    private Connection con;
    private Statement ste;

    public ServiceOrganisation() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Organisation u) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `handshake`.`user` ( `username`, `password`, `nomUser`, `prenomUser`,`email`,`nomOrganisation`, `telephone`, `ville`, `rue`, `pays`,`domaine`,`profil`,`roles`)  VALUES ( '" + u.getusername() + "', '" + u.getPassword() + "', '" + u.getNomUser() + "', '" + u.getPrenomUser() + "', '" + u.getEmail() + "', '" + u.getNomOrganisation() + "', '" + u.getTelephone() + "', '" + u.getVille() + "', '" + u.getRue() + "', '" + u.getPays() + "', '" + u.getDomaine() + "', '" + u.getProfil() + "','Organisation ');";
        ste.executeUpdate(requeteInsert);
    }

    public void modifpassword(String password, int id) throws SQLException {

        ste = con.createStatement();
        String sql = "Update user set password ='"
                + password + "' Where roles='Organisation' and id="
                + id;
        ste.executeUpdate(sql);

    }

    public int chercher(Organisation u) throws SQLException {
        int id = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='Organisation' and username='" + u.getusername() + "'");
        while (rs.next()) {
            id = rs.getInt(1);

        }
        return id;
    }
    public Organisation chercherusername(String a) throws SQLException {
      Organisation p=null;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='Organisation' and username='" +a+ "'");
        while (rs.next()) {
                    int id = rs.getInt(1);
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nomUser = rs.getString("nomUser");
                String prenomUser = rs.getString("prenomUser");
                String email = rs.getString("email");
                int telephone = rs.getInt("telephone");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                String pays = rs.getString("pays");
                  String roles = rs.getString("roles");
                String profil = rs.getString("profil");
                  String norg = rs.getString("nomOrganisation");
                String domaine = rs.getString("domaine");
               p=new Organisation(norg, domaine, id, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);

        }
        return p;
    }
    public void supprimer(Organisation u) throws SQLException {
        ste = con.createStatement();
        int id = chercher(u);
        String requeteDelete = "Delete From handshake.user Where id='" + id + "'";
        ste.executeUpdate(requeteDelete);
    }

    public void modifierprofil(String image, int id) throws SQLException {
        ste = con.createStatement();
        String sql = "Update user set profil ='"
                + image + "' Where roles='Organisation' and  id="
                + id;
        ste.executeUpdate(sql);
    }

    public List<Organisation> readAll() throws SQLException {
        List<Organisation> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='Organisation'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String username = rs.getString("username");
            String password = rs.getString("password");
            String nomUser = rs.getString("nomUser");
            String prenomUser = rs.getString("prenomUser");
            String email = rs.getString("email");
            String nomOrganisation = rs.getString("nomOrganisation");
            int telephone = rs.getInt("telephone");
            String ville = rs.getString("ville");
            String rue = rs.getString("rue");
            String pays = rs.getString("pays");
            String domaine = rs.getString("domaine");
            String profil = rs.getString("profil");
            Organisation p = new Organisation(nomOrganisation, domaine, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, profil);
            arr.add(p);
        }
        return arr;
    }

   public ObservableList<Organisation> afficherOrganisation() throws SQLException, ParseException {
        ObservableList<Organisation> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `user` WHERE roles='Organisation'");

        while (rs.next()) {
         
                int user = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nomUser = rs.getString("nomUser");
                String prenomUser = rs.getString("prenomUser");
                String email = rs.getString("email");
                int telephone = rs.getInt("telephone");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                String pays = rs.getString("pays");
                  String roles = rs.getString("roles");
                String profil = rs.getString("profil");
                  String norg = rs.getString("nomOrganisation");
                String domaine = rs.getString("domaine");
              Organisation p=new Organisation(norg, domaine, user, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);
            arr.add(p);
              
               
            
          
            

        }

        return arr;

    }

}
