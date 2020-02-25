/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Refuge;
import Entities.User;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author amisa
 */
public class ServiceRefuge {

    Connection con;
    Statement ste;

    public ServiceRefuge() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Refuge r, int id) {
        try {
            ste = con.createStatement();

            String requetajout = "insert into don(`donId` ,`typeDon` ,`rueRefuge`, `villeRefuge`, `paysRefuge`, `disponibiliteRefuge`, `capaciteRefuge`,`longitude`,`latitude`,`dateDebutRefuge`,`dateFinRefuge`,userId) values('" + r.getDonId() + "','Refuge','" + r.getRueRefuge() + "','" + r.getVilleRefuge() + "','" + r.getPaysRefuge() + "','" + r.getDisponibiliteRefuge() + "','" + r.getCapaciteRefuge() + "','" + r.getLongitude() + "','" + r.getLatitude() + "','" + r.getDate_debut() + "','" + r.getDate_fin() + "','" + r.getUserId() + "');";
            ste.executeUpdate(requetajout);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     *
     * @return
     */
    public int chercher(Refuge u) throws SQLException {
        int id = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where userId='" + chercherUser(u.getUserId()) + "'and  longitude='" + u.getLongitude() + "' and latitude='" + u.getLatitude() + "'");
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }
 public double moyenneR() throws SQLException
    {
        double i=0 , j=0;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from don where typeDon='Refuge'");
        
        while(rs.next())
        {
            
                i += rs.getInt("capaciteRefuge");
                j++;
            
        }
        if(j !=0)
        {
             return i/j;
        }
       return 0;
    }
    public int chercherUser(int id) throws SQLException {

        int x = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user where role='user simple' and UserId='" + id + "'");
        while (rs.next()) {
            x = rs.getInt(1);

        }
        return x;
    }

    public List<Refuge> localiserRefuge() throws SQLException {
    List<Refuge> arr=new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select latitude,longitude from don where typeDon=Refuge");
        while (rs.next()) {
            Double x = rs.getDouble(1);
            Double y = rs.getDouble(2);
            Refuge r = new Refuge(x, y);
            arr.add(r);
        }
        return arr;

    }

    public void supprimer(Refuge r) throws SQLException {
        ste = con.createStatement();
        int id = chercher(r);
        String requeteDelete = "Delete From handshake.don Where userId='" + id + "'";
        ste.executeUpdate(requeteDelete);
    }

    public void modifRefuge(String rue, int disponibilite, int capacite, int id) throws SQLException {

        ste = con.createStatement();
        String sql = "Update don set RueRefuge ='"
                + rue + "', CapaciteRefuge="
                + capacite + ",DisponibiliteRefuge=" + disponibilite + " Where donId="
                + id;
        ste.executeUpdate(sql);

    }

    public List<Refuge> readAll() throws SQLException {
        List<Refuge> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where typeDon='Refuge'");
        while (rs.next()) {
            int donId = rs.getInt(1);
            String rueRefuge = rs.getString("rueRefuge");
            String villeRefuge = rs.getString("villeRefuge");
            String paysRefuge = rs.getString("paysRefuge");
            int disponibiliteRefuge = rs.getInt("disponibiliteRefuge");
            Date datd = java.sql.Date.valueOf(rs.getString("dateDebutRefuge"));
            Date datf = java.sql.Date.valueOf(rs.getString("dateFinRefuge"));
            int capaciteRefuge = rs.getInt("capaciteRefuge");
            Double longitude = rs.getDouble("longitude");
            Double latitude = rs.getDouble("latitude");
            Refuge p = new Refuge(rueRefuge, villeRefuge, paysRefuge, capaciteRefuge, datd.toLocalDate(), datf.toLocalDate(), longitude, latitude, donId, paysRefuge, donId);
            arr.add(p);

        }
        return arr;
    }
    public List<Refuge> gps() throws SQLException {
        List<Refuge> arr = new ArrayList<>();
        ste = con.createStatement();
             ResultSet rs = ste.executeQuery("select * from don where typeDon='Refuge'");

     while (rs.next()) {
            int donId = rs.getInt(1);
            String rueRefuge = rs.getString("rueRefuge");
            String villeRefuge = rs.getString("villeRefuge");
            String paysRefuge = rs.getString("paysRefuge");
            int disponibiliteRefuge = rs.getInt("disponibiliteRefuge");
            Date datd = java.sql.Date.valueOf(rs.getString("dateDebutRefuge"));
            Date datf = java.sql.Date.valueOf(rs.getString("dateFinRefuge"));
            int capaciteRefuge = rs.getInt("capaciteRefuge");
            Double longitude = rs.getDouble("longitude");
            Double latitude = rs.getDouble("latitude");
            Refuge p = new Refuge(rueRefuge, villeRefuge, paysRefuge, capaciteRefuge, datd.toLocalDate(), datf.toLocalDate(), longitude, latitude, donId, paysRefuge, donId);
            arr.add(p);

        }

        

        return arr;

    }
}
