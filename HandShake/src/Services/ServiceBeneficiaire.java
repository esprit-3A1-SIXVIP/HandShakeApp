/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;
import Entities.Beneficiaire;
import IServices.IService;
import IServices.IServiceAide;
import Utils.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceBeneficiaire implements IServiceAide<Beneficiaire>{
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceBeneficiaire() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Beneficiaire a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `handshake`.`beneficiaire` ( `nomBeneficiaire`, `prenomBeneficiaire`, `email`,`dateNaissance`, `ville`, `telephone`,`adresseGPS`,`role`) VALUES ( ?,?, ?, ?,?, ?,?, ?);");
        PS.setString(1, a.getNomBeneficiaire());
        PS.setString(2, a.getPrenomBeneficiaire());
        PS.setString(3,a.getEmail());
        PS.setDate(4,a.getDateNaissance());
        PS.setString(5, a.getVille());
        PS.setInt(6, a.getTelephone());
        PS.setString(7, a.getAdresseGPS());
        PS.setString(8, a.getRole());
        PS.executeUpdate();
    }
    


    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `handshake`.`beneficiaire` WHERE `beneficiaireId`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Beneficiaire a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`beneficiaire` SET `nomBeneficiaire`=?,`prenomBeneficiaire`=? ,`email`=?,`dateNaissance`=?,`ville`=?,`telephone`=?,`adresseGPS`=?,`role`=? WHERE `beneficiaireId`=?");
        PS.setString(1, a.getNomBeneficiaire());
        PS.setString(2, a.getPrenomBeneficiaire());
        PS.setString(3,a.getEmail());
        PS.setDate(4,a.getDateNaissance());
        PS.setString(5, a.getVille());
        PS.setInt(6, a.getTelephone());
        PS.setString(7, a.getAdresseGPS());
        PS.setString(8, a.getRole());
        PS.setInt(10,id);
        PS.executeUpdate();
    }
        public void updatetab(Beneficiaire a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`beneficiaire` SET `nomBeneficiaire`=?,`prenomBeneficiaire`=? ,`email`=?,`dateNaissance`=?,`ville`=?,`telephone`=?,`adresseGPS`=?,`role`=? WHERE `beneficiaireId`=?");
        PS.setString(1, a.getNomBeneficiaire());
        PS.setString(2, a.getPrenomBeneficiaire());
        PS.setString(3,a.getEmail());
        PS.setDate(4,a.getDateNaissance());
        PS.setString(5, a.getVille());
        PS.setInt(6, a.getTelephone());
        PS.setString(7, a.getAdresseGPS());
        PS.setString(8, a.getRole());
        PS.setInt(10,a.getBeneficiaireId());
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceBeneficiaire.class.getName()).log(Level.SEVERE,null,e);
            }

    }

    @Override
    public List<Beneficiaire> readAll() throws SQLException {
        List<Beneficiaire> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from beneficiaire");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nomBeneficiaire");
            String prenom = rs.getString("prenomBeneficiaire");
            String email = rs.getString("email");
            java.sql.Date datenaiss = rs.getDate("dateNaissance");
            String ville = rs.getString("ville");
            int numtel = rs.getInt(7);
            String adresseGPS = rs.getString("adresseGPS");
            String role = rs.getString("role");
            Beneficiaire a = new Beneficiaire(id,nom,prenom,email,datenaiss,ville,numtel,adresseGPS,role);
            AL.add(a);
        }
        return AL;
    }
    
    public List<Beneficiaire> getTrier() throws SQLException {
    List<Beneficiaire> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from beneficiaire ORDER BY nomBeneficiaire DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nomBeneficiaire");
            String prenom = rs.getString("prenomBeneficiaire");
            String email = rs.getString("email");
            java.sql.Date datenaiss = rs.getDate("dateNaissance");
            String ville = rs.getString("ville");
            int numtel = rs.getInt(7);
            String adresseGPS = rs.getString("adresseGPS");
            String role = rs.getString("role");
            Beneficiaire a = new Beneficiaire(id,nom,prenom,email,datenaiss,ville,numtel,adresseGPS,role);
     arr.add(a);
     }
    return arr;
    }

   public Beneficiaire getById(int id) {
          Beneficiaire a = null;
         String requete = " select* from beneficiaire  where beneficiaireId='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Beneficiaire(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getDate(5),res.getString(6),res.getInt(7),res.getString(8),res.getString(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBeneficiaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Beneficiaire getByName(String n) {
          Beneficiaire a = null;
         String requete = " select* from beneficiaire  where (nomBeneficiaire like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Beneficiaire(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getDate(5),res.getString(6),res.getInt(7),res.getString(8),res.getString(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBeneficiaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
   
}

