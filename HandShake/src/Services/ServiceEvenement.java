/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.User;
import IServices.IService;
import Utils.DataBase;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Soreilla
 */
public class ServiceEvenement implements IService<Evenement> {

    private Connection con;
    private Statement ste;
    
    public ServiceEvenement(){
        con=DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Evenement t) throws SQLException {
        ste = con.createStatement();
        String query="INSERT INTO `handshake`.`evenement`(`evenementId`,`descriptionEvenement`,`lieuEvenement`,`dateEvenement`,`heureEvenement`,`porteeEvenement`,`prixEvenement`) VALUES(NULL,'"+t.getDescriptionEvenement()+"','"+t.getLieuEvenement()+"','"+t.getDateEvenement()+"','"+t.getHeureEvenement()+"','"+t.getPorteeEvenement()+"','"+t.getPrixEvenement()+"');";
         ste.executeUpdate(query);
    
    }

    @Override
    public boolean delete(Evenement t) throws SQLException {
        
                PreparedStatement prepare = con.prepareStatement("DELETE FROM evenement WHERE evenementId= '"+t.getEvenementId()+"'");
                prepare.executeUpdate();

      return true;
    }

    @Override
    public boolean update(Evenement t) throws SQLException {
/*
         ste=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         PreparedStatement prepare = con.prepareStatement("UPDATE evenement set descriptionEvenement= ?, lieuEvenement=?,dateEvenement=?,heureEvenement=?,porteeEvenement=?,prixEvenement=? "+"WHERE evenementId= '"+t.getEvenementId()+"'");
        /*  String query = "SELECT descriptionEvenement, lieuEvenement,dateEvenement,heureEvenement,porteeEvenement,prixEvenement FROM evenement "+"WHERE evenementId ='"+t.getEvenementId()+"'"; 
                ResultSet res = ste.executeQuery(query);
         res.first();*/
       /*  prepare.setString(1, t.getDescriptionEvenement());
         prepare.setString(2, t.getLieuEvenement());
         prepare.setDate(3, (LocalDate) t.getDateEvenement());
         prepare.setTime(4,t.getHeureEvenement());
         prepare.setString(5,t.getPorteeEvenement());
         prepare.setFloat(6, t.getPrixEvenement());
         prepare.executeUpdate();*/
         return true;
    }
     


    @Override
    public ObservableList<Evenement> readAll() throws SQLException {
        ObservableList<Evenement> arr =FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("SELECT descriptionEvenement,lieuEvenement,dateEvenement,heureEvenement,porteeEvenement,prixEvenement FROM evenement");
        while(rs.next()){
           
            String descriptionEvenement= rs.getString(1);
            String lieuEvenement= rs.getString(2);
            java.sql.Date dateEvenement = java.sql.Date.valueOf(rs.getString(3));
             java.sql.Time heureEvenement = java.sql.Time.valueOf(rs.getString(4));
           String porteeEvenement= rs.getString(5);
            Float prixEvenement =rs.getFloat(6);
            Evenement e= new Evenement(
                
                    descriptionEvenement,lieuEvenement,dateEvenement.toLocalDate(),heureEvenement.toLocalTime(),porteeEvenement,prixEvenement);
            arr.add(e);
        }
        return arr;
    }
   
    public void particper(Evenement a, User b) throws SQLException {
        ste = con.createStatement();
        String query="INSERT INTO `handshake`.`participation`(`userId`,`evenementId`) VALUES('"+b.getUserId()+"','"+a.getEvenementId()+"')";
         ste.executeUpdate(query);
        
    }

    
    public List<String> readParticipant(Evenement t) throws SQLException {
    List<String> arr=new ArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select E.descriptionEvenement, U.nomUser, U.prenomUser\n" +
                                        "from evenement E, user U, participation P\n" +
                                        "where E.evenementId=P.evenementId and U.userId=P.userId and E.evenementId='"+t.getEvenementId()+"'");
        while(rs.next()){
           
             String descriptionEvenement= rs.getString(1);
             String nomUser= rs.getString(2);
             String prenomUser= rs.getString(3);
            
       
           arr.add(descriptionEvenement);
           arr.add(nomUser);
           arr.add(prenomUser); 
        }
        return arr;
    }

    
}
