
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
import java.time.LocalDate;
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
            ResultSet rs = ste.executeQuery("select max(id) from user ");
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
        ResultSet rs = ste.executeQuery("select * from user where id=" + id );
        if (rs.next()) {
            User U = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("roles"),rs.getInt("accesShakeHub"));
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
            return rs.getInt("id");
        }

        return -1;
    }

    public int getIdUser1(String a1, String a2) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where email='" + a1 + "' and  password='" + a2 + "'");
        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }

    public String getroles(int a) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where id=" + a);
        if (rs.next()) {
            return rs.getString("roles");
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
        ResultSet rs = ste.executeQuery("select * from don where id='" + idU + "'");

        while (rs.next()) {
            if (rs.getString("typeDon").equals("Nature")) {
                int id = rs.getInt("donId");
                int user = rs.getInt("id");
                String libelle = rs.getString("libelleDonNature");
                String type = rs.getString("typeDon");
                String categorie = rs.getString("categorieDonNature");
                int quantite = rs.getInt("quantiteDonNature");
                String cible = rs.getString("cibleDon");

                Date date1 = rs.getDate("dateDon");
                DonNature dn = new DonNature(libelle, categorie, quantite, id, type, cible, date1, user);

                arr.add(dn);
            } else if (rs.getString("typeDon").equals("Especes")) {
                int id = rs.getInt(1);

                String cible = rs.getString("cibleDon");
                String type = rs.getString("typeDon");
                int montant = rs.getInt("montantDon");

                Date date1 = rs.getDate("dateDon");
                int user = rs.getInt("id");
                DonEspeces de = new DonEspeces(id, montant, type, cible, user, date1);

                arr.add(de);
            } 

        }

        return arr;

    }

    public ObservableList<Dons> readAllDonAdmin() throws SQLException, ParseException {
        ObservableList<Dons> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don ");

        while (rs.next()) {
            if (rs.getString("typeDon").equals("Nature")) {
                int id = rs.getInt("donId");
                int user = rs.getInt("id");
                String libelle = rs.getString("libelleDonNature");
                String type = rs.getString("typeDon");
                String categorie = rs.getString("categorieDonNature");
                int quantite = rs.getInt("quantiteDonNature");
                String cible = rs.getString("cibleDon");

                Date date1 = rs.getDate("dateDon");
                DonNature dn = new DonNature(libelle, categorie, quantite, id, type, cible, date1, user);

                arr.add(dn);

            } else if (rs.getString("typeDon").equals("Especes")) {
                int id = rs.getInt(1);

                String cible = rs.getString("cibleDon");
                String type = rs.getString("typeDon");
                int montant = rs.getInt("montantDon");

                Date date1 = rs.getDate("dateDon");
                int user = rs.getInt("id");
                DonEspeces de = new DonEspeces(id, montant, type, cible, user, date1);
                arr.add(de);
            } else {
                int id = rs.getInt("donId");
                int user = rs.getInt("id");
                String type = rs.getString("typeDon");
                String cible = rs.getString("cibleDon");
                String rueRefuge = rs.getString("rueRefuge");
                String villeRefuge = rs.getString("villeRefuge");
                String paysRefuge = rs.getString("paysRefuge");
                int disponibiliteRefuge = rs.getInt("disponibiliteRefuge");
                java.sql.Date datd = java.sql.Date.valueOf(rs.getString("dateDebutRefuge"));
                java.sql.Date datf = java.sql.Date.valueOf(rs.getString("dateFinRefuge"));
                int capaciteRefuge = rs.getInt("capaciteRefuge");
                Double longitude = rs.getDouble("longitude");
                Double latitude = rs.getDouble("latitude");
                Refuge p = new Refuge(rueRefuge, villeRefuge, paysRefuge, disponibiliteRefuge, capaciteRefuge, datd.toLocalDate(), datf.toLocalDate(), longitude, latitude, id, type, cible, user);
                arr.add(p);
            }

        }

        return arr;

    }
     public ObservableList<User> readOrganisation() throws SQLException {
        ObservableList<User> arr =FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select nomOrganisation,pays,ville,domaine,email from user\n" +
"where roles=\"organisation\";");
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
        String requeteInsert = "INSERT INTO `handshake`.`user` ( `id`,`username`, `password`, `nomUser`, `prenomUser`, `email`, `telephone`, `ville`, `rue`, `pays`,`profil`, `roles`)  VALUES ( '" + u.getid() + "','" + u.getusername() + "', '" + u.getPassword() + "', '" + u.getNomUser() + "', '" + u.getPrenomUser() + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getVille() + "', '" + u.getRue() + "', '" + u.getPays() + "', '" + u.getProfil() + "','User Simple');";

        ste.executeUpdate(requeteInsert);
    }

    public int chercher(User u) throws SQLException {
        int id = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='User Simple' and username='" + u.getusername() + "'");
        while (rs.next()) {
            id = rs.getInt(1);

        }
        return id;
    }
//attention pour connnexion username au lieu d'email

    public int getIdUser2(String a1, String a2) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where username='" + a1 + "' and  password='" + a2 + "'");
        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }

    public void supprimer(User u) throws SQLException {
        ste = con.createStatement();
        int id = chercher(u);
        String requeteDelete = "Delete From handshake.user Where roles='User Simple' and id='" + id + "'";
        ste.executeUpdate(requeteDelete);
    }

    public User chercherUser(String a) throws SQLException {
        User p = null;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='User Simple' and username='" + a + "'");
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

            p = new User(id, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);

        }
        return p;
    }

    public void modifpassword(String password, int id) throws SQLException {

        ste = con.createStatement();
        String sql = "Update user set password =" + password + " Where roles='User Simple' and id=" + id;
        ste.executeUpdate(sql);

    }

    public void modifierprofil(String image, User u) throws SQLException {
        ste = con.createStatement();
        String sql = "Update user set profil ='" + image + "' Where roles='User Simple' and id=" + chercher(u);
        ste.executeUpdate(sql);
    }

    public boolean update(int id, String nom, String prenom, String email, String rue, String ville, String profil, String username, String pays) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "UPDATE user SET ville='" + ville
                + "',nomUser='" + nom
                + "',prenomUser='" + prenom
                + "',email='" + email
                + "',username='" + username
                + "',rue='" + rue
                + "',pays='" + pays
                + "',profil='" + profil
                + "' WHERE id=" + id;
        ste.executeUpdate(requeteInsert);
        System.out.println("Modification effectuer");
        return true;
    }
    /**
     *
     * @return @throws SQLException
     */
    public List<User> readAll() throws SQLException {
        List<User> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='user simple'");
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
            User p = new User(username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles);
            arr.add(p);

        }
        return arr;
    }

    public ObservableList<Organisation> afficherOrganisation() throws SQLException, ParseException {
        ObservableList<Organisation> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user ");

        if (rs.getString("roles").equals("Organisation")) {
            int id = rs.getInt(1);

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
            Organisation de = new Organisation(norg, domaine, user, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);

            arr.add(de);
        }
        return arr;
    }

    public ObservableList<User> afficherUser() throws SQLException, ParseException {
        ObservableList<User> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user ");

        while (rs.next()) {
            if (rs.getString("roles").equals("User Simple")) {
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
                User p = new User(user, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);
                arr.add(p);

            }

        }

        return arr;

    }

    public String getusername(int a) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where id=" + a);
        if (rs.next()) {
            return rs.getString("username");
        }

        return null;
    }

    public int NombreDonNature() throws SQLException {
        int i = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Nature'");
        while (rs.next()) {
            i++;
        }
        return i;
    }

    public int NombreDonRefuge() throws SQLException {
        int i = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Refuge'");
        while (rs.next()) {
            i++;
        }
        return i;
    }

    public boolean setAccessShakeHub(User U) throws SQLException {
       ste = con.createStatement();
       String requeteUpdate = "UPDATE `handshake`.`user` SET `accesShakeHub` = '" + U.isAccesShakeHub() + "' WHERE `id`= '" + U.getid() + "';";
       return(ste.execute(requeteUpdate)); 
    }

    public User chercherUser(int id) throws SQLException {
        User p = null;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where roles='User Simple' and id='" + id + "'");
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
            p = new User(userId, username, password, nomUser, prenomUser, email, telephone, ville, rue, pays, roles, profil);

        }
        return p;
    }

    public int NombreDonEspece() throws SQLException {
        int i = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Especes'");
        while (rs.next()) {
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
