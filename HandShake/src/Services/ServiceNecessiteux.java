
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
import Utils.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Necessiteux;
import Entities.Refugie;
import IServices.IServiceAide;


public class ServiceNecessiteux implements IServiceAide<Necessiteux>{
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceNecessiteux() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Necessiteux a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `handshake`.`beneficiaire` ( `aideId`,`nomBeneficiaire`, `prenomBeneficiaire`, `email`,`dateNaissance`, `ville`, `telephone`,`adresseGPS`,`roles`,`besoin`) VALUES ( ?,?, ?, ?,?, ?,?,?,?, ?);");
        PS.setInt(1, a.getAideId());
        PS.setString(2, a.getNomBeneficiaire());
        PS.setString(3, a.getPrenomBeneficiaire());
        PS.setString(4,a.getEmail());
        PS.setDate(5,a.getDateNaissance());
        PS.setString(6, a.getVille());
        PS.setInt(7, a.getTelephone());
        PS.setString(8, a.getAdresseGPS());
        PS.setString(9, a.getroles());
        PS.setString(10, a.getBesoin());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `handshake`.`beneficiaire` WHERE `beneficiaireId`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Necessiteux a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`beneficiaire` SET `aideId`,`nomBeneficiaire`=?,`prenomBeneficiaire`=? ,`email`=?,`dateNaissance`=?,`ville`=?,`telephone`=?,`adresseGPS`=?,`besoin`=? WHERE `beneficiaireId`=?");
        PS.setInt(1, a.getAideId());
        PS.setString(2, a.getNomBeneficiaire());
        PS.setString(3, a.getPrenomBeneficiaire());
        PS.setString(4,a.getEmail());
        PS.setDate(5,a.getDateNaissance());
        PS.setString(6, a.getVille());
        PS.setInt(7, a.getTelephone());
        PS.setString(8, a.getAdresseGPS());
        PS.setString(9, a.getBesoin());
        PS.setInt(10, id);
        PS.executeUpdate();
        }
        public void updatetab(Necessiteux a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `handshake`.`beneficiaire` SET `nomBeneficiaire`=?,`prenomBeneficiaire`=? ,`email`=?,`dateNaissance`=?,`ville`=?,`telephone`=?,`adresseGPS`=?,`besoin`=? WHERE `beneficiaireId`=?");
        PS.setString(1, a.getNomBeneficiaire());
        PS.setString(2, a.getPrenomBeneficiaire());
        PS.setString(3,a.getEmail());
        PS.setDate(4,a.getDateNaissance());
        PS.setString(5, a.getVille());
        PS.setInt(6, a.getTelephone());
        PS.setString(7, a.getAdresseGPS());
        PS.setString(8, a.getBesoin());
        PS.setInt(9, a.getBeneficiaireId());
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceNecessiteux.class.getName()).log(Level.SEVERE,null,e);
            }

    }
    public int calculer() {
          int l = 0 ;
         String requete = "SELECT COUNT(besoin) FROM beneficiaire " ;
        try {
           
           Statement st =con.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNecessiteux.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }

    @Override
    public List<Necessiteux> readAll() throws SQLException {
        List<Necessiteux> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from beneficiaire");
        while (rs.next()) {
            int id = rs.getInt(1);
            int idaide = rs.getInt(2);
            String nom = rs.getString("nomBeneficiaire");
            String prenom = rs.getString("prenomBeneficiaire");
            String email = rs.getString("email");
            java.sql.Date datenaiss = rs.getDate("dateNaissance");
            String ville = rs.getString("ville");
            int numtel = rs.getInt(8);
            String adresseGPS = rs.getString("adresseGPS");
            String roles = rs.getString("roles");
            String bes = rs.getString("besoin");
            Necessiteux a = new Necessiteux(bes,id,idaide,nom,prenom,email,datenaiss,ville,numtel,adresseGPS,roles);
            AL.add(a);
        }
        return AL;
    }
            public List<Necessiteux> readn() throws SQLException {
        List<Necessiteux> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from beneficiaire where `roles`= \"Necessiteux\"");
        while (rs.next()) {
            int id = rs.getInt(1);
            int idaide = rs.getInt(2);
            String nom = rs.getString("nomBeneficiaire");
            String prenom = rs.getString("prenomBeneficiaire");
            String email = rs.getString("email");
            java.sql.Date datenaiss = rs.getDate("dateNaissance");
            String ville = rs.getString("ville");
            int numtel = rs.getInt(8);
            String adresseGPS = rs.getString("adresseGPS");
            String roles = rs.getString("roles");
            String bes = rs.getString("besoin");
            Necessiteux a = new Necessiteux(bes,id,idaide,nom,prenom,email,datenaiss,ville,numtel,adresseGPS,roles);
            AL.add(a);
        }
        return AL;
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
            Logger.getLogger(ServiceNecessiteux.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServiceNecessiteux.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
   
}
