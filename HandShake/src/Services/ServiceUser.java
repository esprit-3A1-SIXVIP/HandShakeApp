
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DonEspeces;
import Entities.DonNature;
import Entities.Dons;
import Entities.Organisation;
import Entities.Refuge;
import Entities.User;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.DateStringConverter;

/**
 *
 * @author steph
 */
public class ServiceUser {

    private Connection con;
    private Statement ste;

    public ServiceUser() {
        con = DataBase.getInstance().getConnection();

    }

    public final int autoIncrementeIdUser() {
        int x = 1;
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select max(userId) from user ");
            while (rs.next()) {
                x = rs.getInt(1) + 1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }

 
    
    public User getUser(int id) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where userId=" + id );
        if (rs.next()) {
            User U = new User(rs.getInt("userId"),rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getString("role"));
            return U;
        }

        return null;
    }
  

    /**
     *
     * @return @throws SQLException
     */

    public int getIdUser(User u) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where email='" + u.getEmail() + "' and  password='" + u.getPassword() + "'");
        if (rs.next()) {
            return rs.getInt("userId");
        }

        return -1;
    }
    
     public int getIdUser1(String a1 , String a2) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where email='" + a1 + "' and  password='" + a2 + "'");
        if (rs.next()) {
            return rs.getInt("userId");
        }

        return -1;
    }
     
     public String getRole(int a) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where userId="+a);
        if (rs.next()) {
            return rs.getString("role");
        }

        return null;
    }

    /**
     *
     * @param idU
     * @return
     * @throws SQLException
     */
    public ObservableList<Dons> readAllDon(int idU) throws SQLException, ParseException {
        ObservableList<Dons> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where userId='" + idU + "'");

        while (rs.next()) {
            if (rs.getString("typeDon").equals("Nature")) {
                int id = rs.getInt("donId");
                int user = rs.getInt("userId");
                String libelle = rs.getString("libelleDonNature");
                String type = rs.getString("typeDon");
                String categorie = rs.getString("categorieDonNature");
                int quantite = rs.getInt("quantiteDonNature");
                String cible = rs.getString("cibleDon");
                
                Date date1 = rs.getDate("dateDon");
                DonNature dn = new DonNature(libelle, categorie, quantite, id, type, cible, date1, user);

                arr.add(dn);
            } else  if(rs.getString("typeDon").equals("Especes"))
                    {
                int id = rs.getInt(1);

                String cible = rs.getString("cibleDon");
                String type = rs.getString("typeDon");
                int montant = rs.getInt("montantDon");
                 
                Date date1 = rs.getDate("dateDon");
                int user = rs.getInt("userId");
                DonEspeces de = new DonEspeces(id, montant, type, cible, user, date1);

                arr.add(de);
            }
            else
            {
                 int id = rs.getInt("donId");
                int user = rs.getInt("userId");
            String rueRefuge = rs.getString("rueRefuge");
            String villeRefuge = rs.getString("villeRefuge");
            String paysRefuge = rs.getString("paysRefuge");
            int disponibiliteRefuge = rs.getInt("disponibiliteRefuge");
            java.sql.Date datd=java.sql.Date.valueOf(rs.getString("dateDebutRefuge"));
            java.sql.Date datf=java.sql.Date.valueOf(rs.getString("dateFinRefuge"));
            int capaciteRefuge = rs.getInt("capaciteRefuge");
            Double longitude=rs.getDouble("longitude");
            Double latitude=rs.getDouble("latitude");
            Refuge p = new Refuge(rueRefuge, villeRefuge, paysRefuge, capaciteRefuge, datd.toLocalDate(), datf.toLocalDate(), longitude, latitude, id, paysRefuge, user);
            arr.add(p);
            }

        }

        return arr;

    }
    
    public ObservableList<Dons> readAllDonAdmin() throws SQLException, ParseException {
        ObservableList<Dons> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don ");

        while (rs.next()) {
            if (rs.getString("typeDon").equals("Nature")) 
            {
                int id = rs.getInt("donId");
                int user = rs.getInt("userId");
                String libelle = rs.getString("libelleDonNature");
                String type = rs.getString("typeDon");
                String categorie = rs.getString("categorieDonNature");
                int quantite = rs.getInt("quantiteDonNature");
                String cible = rs.getString("cibleDon");
                
                Date date1 = rs.getDate("dateDon");
                DonNature dn = new DonNature(libelle, categorie, quantite, id, type, cible, date1, user);

                arr.add(dn);
            
                 }
             else  if(rs.getString("typeDon").equals("Especes"))
            {
                int id = rs.getInt(1);

                String cible = rs.getString("cibleDon");
                String type = rs.getString("typeDon");
                int montant = rs.getInt("montantDon");
                 
                Date date1 = rs.getDate("dateDon");
                int user = rs.getInt("userId");
                DonEspeces de = new DonEspeces(id, montant, type, cible, user, date1);
                arr.add(de);
            }
            else
            {
                 int id = rs.getInt("donId");
                int user = rs.getInt("userId");
            String rueRefuge = rs.getString("rueRefuge");
            String villeRefuge = rs.getString("villeRefuge");
            String paysRefuge = rs.getString("paysRefuge");
            int disponibiliteRefuge = rs.getInt("disponibiliteRefuge");
            java.sql.Date datd=java.sql.Date.valueOf(rs.getString("dateDebutRefuge"));
            java.sql.Date datf=java.sql.Date.valueOf(rs.getString("dateFinRefuge"));
            int capaciteRefuge = rs.getInt("capaciteRefuge");
            Double longitude=rs.getDouble("longituge");
            Double latitude=rs.getDouble("latitude");
            Refuge p = new Refuge(rueRefuge, villeRefuge, paysRefuge, capaciteRefuge, datd.toLocalDate(), datf.toLocalDate(), longitude, latitude, id, paysRefuge, user);
            arr.add(p);
            }

        }

        

        return arr;

    }
/*     public ObservableList<User> readOrganisation() throws SQLException {
        ObservableList<User> arr =FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select nomOrganisation,pays,ville,domaine,email from user\n" +
"where type=\"organisation\";");
        while(rs.next()){
            
            String nomOrganisation= rs.getString("nomOrganisation");
            String ville= rs.getString("ville");
           String pays= rs.getString("pays");
           String domaine= rs.getString("domaine");
           String email= rs.getString("email");
          
           User u = new User(nomOrganisation,ville,domaine,pays,email);
            arr.add(u);
        }
        return arr;
    }*/
public void ajouter(User u) throws SQLException {

        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `handshake`.`user` ( `userId`,`login`, `password`, `nomUser`, `prenomUser`, `email`, `telephone`, `ville`, `rue`, `pays`,`profil`, `role`)  VALUES ( '" + u.getUserId() + "','" + u.getLogin() + "', '" + u.getPassword() + "', '" + u.getNomUser() + "', '" + u.getPrenomUser() + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getVille() + "', '" + u.getRue() + "', '" + u.getPays() + "', '" + u.getProfil() + "','User Simple');";

        ste.executeUpdate(requeteInsert);
    }

    public int chercher(User u) throws SQLException {
        int id = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where role='User Simple' and login='" + u.getLogin() + "'");
        while (rs.next()) {
            id = rs.getInt(1);

        }
        return id;
    }
//attention pour connnexion login au lieu d'email
    public int getIdUser2(String a1, String a2) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where login='" + a1 + "' and  password='" + a2 + "'");
        if (rs.next()) {
            return rs.getInt("userId");
        }

        return -1;
    }

    public void supprimer(User u) throws SQLException {
        ste = con.createStatement();
        int id = chercher(u);
        String requeteDelete = "Delete From handshake.user Where role='User Simple' and userId='" + id + "'";
        ste.executeUpdate(requeteDelete);
    }

    public User chercherUser(String a) throws SQLException {
        User p = null;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where role='User Simple' and login='" + a + "'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String login = rs.getString("login");
            String password = rs.getString("password");
            String nomUser = rs.getString("nomUser");
            String prenomUser = rs.getString("prenomUser");
            String email = rs.getString("email");
            int telephone = rs.getInt("telephone");
            String ville = rs.getString("ville");
            String rue = rs.getString("rue");
            String pays = rs.getString("pays");
            String role = rs.getString("role");
            String profil = rs.getString("profil");

            p = new User(id, login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role, profil);

        }
        return p;
    }

    public void modifpassword(String password, int id) throws SQLException {

        ste = con.createStatement();
        String sql = "Update user set password =" + password + " Where role='User Simple' and userId=" + id;
        ste.executeUpdate(sql);

    }

    public void modifierprofil(String image, User u) throws SQLException {
        ste = con.createStatement();
        String sql = "Update user set profil ='" + image + "' Where role='User Simple' and userId=" + chercher(u);
        ste.executeUpdate(sql);
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<User> readAll() throws SQLException {
        List<User> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where role='user simple'");
        while (rs.next()) {
            int userId = rs.getInt(1);
            String login = rs.getString("login");
            String password = rs.getString("password");
            String nomUser = rs.getString("nomUser");
            String prenomUser = rs.getString("prenomUser");
            String email = rs.getString("email");
            int telephone = rs.getInt("telephone");
            String ville = rs.getString("ville");
            String rue = rs.getString("rue");
            String pays = rs.getString("pays");
            String role = rs.getString("role");
            User p = new User(login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role);
            arr.add(p);

        }
        return arr;
    }

    public ObservableList<Organisation> afficherOrganisation() throws SQLException, ParseException {
        ObservableList<Organisation> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user ");

        if (rs.getString("role").equals("Organisation")) {
            int id = rs.getInt(1);

            int user = rs.getInt("userId");
            String login = rs.getString("login");
            String password = rs.getString("password");
            String nomUser = rs.getString("nomUser");
            String prenomUser = rs.getString("prenomUser");
            String email = rs.getString("email");
            int telephone = rs.getInt("telephone");
            String ville = rs.getString("ville");
            String rue = rs.getString("rue");
            String pays = rs.getString("pays");
            String role = rs.getString("role");
            String profil = rs.getString("profil");
            String norg = rs.getString("nomOrganisation");
            String domaine = rs.getString("domaine");
            Organisation de = new Organisation(norg, domaine, user, login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role, profil);

            arr.add(de);
        }
        return arr;
    }

    public ObservableList<User> afficherUser() throws SQLException, ParseException {
        ObservableList<User> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user ");

        while (rs.next()) {
            if (rs.getString("role").equals("User Simple")) {
                int user = rs.getInt("userId");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String nomUser = rs.getString("nomUser");
                String prenomUser = rs.getString("prenomUser");
                String email = rs.getString("email");
                int telephone = rs.getInt("telephone");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                String pays = rs.getString("pays");
                String role = rs.getString("role");
                String profil = rs.getString("profil");
                User p = new User(user, login, password, nomUser, prenomUser, email, telephone, ville, rue, pays, role, profil);
                arr.add(p);

            }

        }

        return arr;

    }




    

     

     
     public String getLogin(int a) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where userId="+a);
        if (rs.next()) {
            return rs.getString("login");
        }

        return null;
    }

   
    public int NombreDonNature() throws SQLException
    {
        int i=0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Nature'");
        while(rs.next())
        {
            i++;
        }
        return i;
    }
    
    public int NombreDonEspece() throws SQLException
    {
        int i=0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Especes'");
        while(rs.next())
        {
            i++;
        }
        return i;
    }

//    public List<Dons> recherche(String type, String cible) throws SQLException
//    {   
//        List<Dons> arr = new ArrayList<>();
//        ste = con.createStatement();
//        ResultSet rs = ste.executeQuery("select * from don where typeDon="+type+"AND cibleDon="+cible+"");
//        
//        if(type.equals("Nature"))
//        {
//        while (rs.next()) {  
//         
//             int id=rs.getInt(1);
//             String libelle=rs.getString("libelleDonNature");
//             String type1=rs.getString("typeDon");
//             String categorie=rs.getString("categorieDonNature");
//             int quantite=rs.getInt("quantiteDonNature");
//             String cible1=rs.getString("cibleDon");
//             DonNature dn=new DonNature(libelle, categorie, quantite, id, type1, cible1);
//             
//             arr.add(dn);
//         
//          
//     }
//        }
//        else
//        {
//            while (rs.next()) {  
//       
//              int id=rs.getInt(1);
//              
//               String cible2=rs.getString("cibleDon");
//               String type2=rs.getString("typeDon");
//               int montant=rs.getInt("montantDon");
//               DonEspeces de=new DonEspeces(id,montant, type2, cible2);
//                
//     arr.add(de);
//         
//          
//     }
//        }
//     
//    return arr;
//    
//        
//    }
}


