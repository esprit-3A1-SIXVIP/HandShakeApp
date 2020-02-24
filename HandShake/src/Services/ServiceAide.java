/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;
import Entities.Aide;
import IServices.IService;
import Utils.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceAide implements IService<Aide> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceAide() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Aide a) throws SQLException {
    PreparedStatement PS=con.prepareStatement("INSERT INTO `handshake`.`aide` (`descriptionAide`,`localisationAide`,`categorieAide`) VALUES (?,?,?);");
        PS.setString(1, a.getDescriptionAide());
        PS.setString(2, a.getLocalisationAide());
        PS.setString(3, a.getCategorieAide());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `handshake`.`aide` WHERE `aideId`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Aide a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`aide` SET `descriptionAide`=?,`localisationAide`=? ,`categorieAide`=? WHERE `aideId`=?");
        PS.setString(1,a.getDescriptionAide());
        PS.setString(2, a.getDescriptionAide());
        PS.setString(3,a.getLocalisationAide());
        PS.setInt(4,id);
        PS.executeUpdate();
    }

        public void updatetab(Aide a) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`aide` SET `descriptionAide`=?,`localisationAide`=? ,`categorieAide`=? WHERE `aideId`=?");
        PS.setString(1,a.getDescriptionAide());
        PS.setString(2, a.getDescriptionAide());
        PS.setString(3,a.getLocalisationAide());
        PS.setInt(4,a.getAideID());
        PS.executeUpdate();
    }

    @Override
    public List<Aide> readAll() throws SQLException {
        List<Aide> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from aide");
        while (rs.next()) {
            int id = rs.getInt(1);
            String descriptionAide = rs.getString("descriptionAide");
            String localisationAide = rs.getString("localisationAide");
            String categorieAide = rs.getString("categorieAide");
            Aide a = new Aide(id, descriptionAide,localisationAide , categorieAide);
            AL.add(a);
        }
        return AL;
    }    
    public List<Integer> readid() throws SQLException {
        List<Integer> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from aide");
        while (rs.next()) {
            int id = rs.getInt(1);
            Aide a = new Aide(id);
            AL.add(a.getAideID());
        }
        return AL;
    }
    
    public List<Aide> getTrier() throws SQLException {
            List<Aide> AL = new ArrayList<>();
        ste = con.createStatement();
    ResultSet rs=ste.executeQuery("select * from aide ORDER BY categorieAide DESC");
            while (rs.next()) {
            int id = rs.getInt(1);
            String descriptionAide = rs.getString("descriptionAide");
            String localisationAide = rs.getString("localisationAide");
            String categorieAide = rs.getString("categorieAide");
            Aide a = new Aide(id, descriptionAide,localisationAide , categorieAide);
            AL.add(a);
     }
    return AL;
    }
  public Aide getById(int id) {
          Aide a = null;
         String requete = " select* from aide where aideId='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Aide(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAide.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Aide getByName(String n) {
          Aide a = null;
         String requete = " select* from aide  where (categorieAide like '"+n+"%')" ;
         try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Aide(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAide.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
}
