/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.User;
import java.sql.Statement;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amisa
 */
public class ServiceAdmin {

    private Connection con;
    private Statement ste;

    public ServiceAdmin() {
        con = DataBase.getInstance().getConnection();

    }



    public void ajouter(Admin u) throws SQLException {
        ste = con.createStatement();

        String requeteInsert = "INSERT INTO `handshake`.`user` ( `username`, `password`, `nomUser`, `prenomUser`, `email`, `telephone`, `ville`, `rue`, `pays`,`profil`,`roles`)  VALUES ( '" + u.getusername() + "', '" + u.getPassword() + "', '" + u.getNomUser() + "', '" + u.getPrenomUser() + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getVille() + "', '" + u.getRue() + "', '" + u.getPays() + "','" + u.getProfil() + "','Administrateur');";
     
        ste.executeUpdate(requeteInsert);
    }

public int chercher (Admin u) throws SQLException
{
        int id=0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where  roles=' Administrateur' and username='"+u.getusername()+"'");
        while (rs.next()) {
            id = rs.getInt(1);
      
        }
         return id;
        }
     


    public void supprimer(Admin u) throws SQLException {
        ste = con.createStatement();
       int id=chercher(u);
        String requeteDelete = "Delete From handshake.user Where  roles=' Administrateur' and id='"+id+"'";
        ste.executeUpdate(requeteDelete);
    }
public void modifpassword(String password,int id) throws SQLException{
             
            ste=con.createStatement();
            String sql ="Update user set password ='"
                    +password+"' Where roles='Administrateur' and id="
                    +id;
                ste.executeUpdate(sql);
            
    }
        public int getIdAdmin(String a1 , String a2) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='Administrateur' and  email='" + a1 + "' and  password='" + a2 + "'");
        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }
     public void modifierprofil(String image,int id) throws SQLException
     {
       ste=con.createStatement();
            String sql ="Update user set profil ='"
                    +image+"' Where roles='Administrateur' and id="
                    +id;
                ste.executeUpdate(sql);
     }
    public List<Admin> readAll() throws SQLException {
        List<Admin> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='Administrateur'");
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
            Admin p = new Admin(username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles);
            arr.add(p);
        }
        return arr;
    }
        public Admin chercherAdmin(int id) throws SQLException {
        Admin p = null;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='admin' and id='" + id+ "'");
        while (rs.next()) {
            int userId = rs.getInt(1);
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
            p = new Admin(userId, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);

        }
        return p;
    }
}
