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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private PreparedStatement pst;
      private FileInputStream fis;
    private File file;
    public ServiceEvenement(){
        con=DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Evenement t) throws SQLException {
       ste = con.createStatement();
       
         String query="INSERT INTO `handshake`.`evenement`(`evenementId`,`descriptionEvenement`,`lieuEvenement`,`dateEvenement`,`heureEvenement`,`porteeEvenement`,`prixEvenement`,`userId`,`image`) VALUES(NULL,'"+t.getDescriptionEvenement()+"','"+t.getLieuEvenement()+"','"+t.getDateEvenement()+"','"+t.getHeureEvenement()+"','"+t.getPorteeEvenement()+"','"+t.getPrixEvenement()+"','"+t.getUserId()+"','"+t.getImage()+"');";
         
         
         ste.executeUpdate(query);
 /* java.sql.Date dateEvenement = java.sql.Date.valueOf(rs.getString(4));
             java.sql.Time heureEvenement = java.sql.Time.valueOf(rs.getString(5));*/
    
    }

    @Override
    public boolean delete(Evenement t) throws SQLException {
        
                PreparedStatement prepare = con.prepareStatement("DELETE FROM evenement WHERE evenementId= '"+t.getEvenementId()+"'");
                prepare.executeUpdate();

      return true;
    }
      public boolean delete(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert= "DELETE FROM `handshake`.`evenement` WHERE evenementId="+id;
        ste.executeUpdate(requeteInsert);
        System.out.println("Supression effectuée");
        return true;
    }
     

    @Override
    public boolean update(Evenement t) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    public boolean update(int evenementId,String descriptionEvenement,String lieuEvenement, LocalDate dateEvenement, LocalTime heureEvenement,String porteeEvenement, float prixEvenement) throws SQLException{
        ste =con.createStatement();
      
                String requeteInsert="UPDATE `handshake`.`evenement`  SET evenementId='"+evenementId
                        + "',descriptionEvenement='"+descriptionEvenement
                +"',lieuEvenement='"+lieuEvenement
               +"',dateEvenement='"+dateEvenement
               +"',heureEvenement='"+heureEvenement
                +"',porteeEvenement='"+porteeEvenement
                +"',prixEvenement="+prixEvenement
                +" WHERE evenementId="+evenementId;
   
                
              ste.executeUpdate(requeteInsert);
        System.out.println("Modification effectuée");
        return true; 
        
    }
    
//descriptionEvenement,lieuEvenement,dateEvenement,heureEvenement,porteeEvenement,prixEvenement

    @Override
    public ObservableList<Evenement> readAll() throws SQLException {
        ObservableList<Evenement> arr =FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("SELECT evenementId,descriptionEvenement,lieuEvenement,dateEvenement,heureEvenement,porteeEvenement,prixEvenement,image FROM evenement");
        while(rs.next()){
            int evenementId =rs.getInt(1);
            String descriptionEvenement= rs.getString(2);
            String lieuEvenement= rs.getString(3);
            java.sql.Date dateEvenement = java.sql.Date.valueOf(rs.getString(4));
             java.sql.Time heureEvenement = java.sql.Time.valueOf(rs.getString(5));
           String porteeEvenement= rs.getString(6);
            Float prixEvenement =rs.getFloat(7);
            String image=rs.getString(8);
            Evenement e= new Evenement(
                
                    evenementId,descriptionEvenement,lieuEvenement,dateEvenement.toLocalDate(),heureEvenement.toLocalTime(),porteeEvenement,prixEvenement,image);
            arr.add(e);
        }
        return arr;
    }
   
   public ObservableList<Evenement> readEvenement(int idU) throws SQLException {
        ObservableList<Evenement> arr =FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("SELECT evenementId,descriptionEvenement,lieuEvenement,dateEvenement,heureEvenement,porteeEvenement,prixEvenement FROM evenement  WHERE userId="+ idU + "");
        while(rs.next()){
            int evenementId =rs.getInt(1);
            String descriptionEvenement= rs.getString(2);
            String lieuEvenement= rs.getString(3);
            java.sql.Date dateEvenement = java.sql.Date.valueOf(rs.getString(4));
             java.sql.Time heureEvenement = java.sql.Time.valueOf(rs.getString(5));
           String porteeEvenement= rs.getString(6);
            Float prixEvenement =rs.getFloat(7);
           /* int userId=rs.getInt(8);
            String image=rs.getString(9);*/
            Evenement e= new Evenement(
                
                    evenementId,descriptionEvenement,lieuEvenement,dateEvenement.toLocalDate(),heureEvenement.toLocalTime(),porteeEvenement,prixEvenement/*,userId,image*/);
            arr.add(e);
        }
        return arr;
    }
   
    public void particper(Evenement a, int id) throws SQLException {
        ste = con.createStatement();
        String query="INSERT INTO `handshake`.`participation`(`userId`,`evenementId`) VALUES("+id+",'"+a.getEvenementId()+"')";
         ste.executeUpdate(query);
        
    }
   
    public int NombreParticpantParEvenement(int eventId)throws SQLException
    {
         int nbr=0;
   ste = con.createStatement();
   ResultSet rs=ste.executeQuery("SELECT COUNT(*) AS nbr from participation  where evenementId="+ eventId+ "");
      while(rs.next()){
          nbr=rs.getInt("nbr");
      }
      return nbr;
      }
  public ObservableList<Evenement> searchEventByIdUser(int idU)throws SQLException{
        ObservableList<Evenement>  arr=FXCollections.observableArrayList();
      ste = con.createStatement();
       ste = con.createStatement();
   ResultSet rs=ste.executeQuery("select evenementId,descriptionEvenement from evenement where userId="+ idU+ "");
          while(rs.next()){
              
              int evenementId=rs.getInt(1);
              String descriptionEvenement= rs.getString(2);
               Evenement e= new Evenement(
                
                    evenementId,descriptionEvenement);
            arr.add(e);
              
          }
      return arr;
  }
  // count = rs3.getInt("count");
   

    
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
