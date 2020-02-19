/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DonEspeces;
import IServices.InterfaceDon;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author steph
 */
public class ServiceDonEspeces implements InterfaceDon<DonEspeces>{

    private Connection con;
    private Statement ste;

    public ServiceDonEspeces() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    
    @Override
    public void ajouter(DonEspeces t) throws SQLException {
         long  millis = System.currentTimeMillis ();  
    java.sql.Date date = new  java.sql.Date (millis);
         PreparedStatement pre=con.prepareStatement("INSERT INTO `handshake`.`don` ( `montantDon`, `typeDon`, `cibleDon`,`userId`,`dateDon`) VALUES ( ?, ?, ?, ?, ?);");
    pre.setInt(1, t.getMontantDon());
    pre.setString(2, "Especes");
    pre.setString(3,t.getCibleDon());
     pre.setInt(4,t.getUserId());
    pre.setDate(5,date );
    pre.executeUpdate();
    }

    @Override
    public boolean delete(DonEspeces dE) throws SQLException {
        ste = con.createStatement();
        String requeteInsert= "DELETE FROM `handshake`.`don` WHERE donId="+dE.getDonId();
        ste.executeUpdate(requeteInsert);
        System.out.println("Supression effectuer");
        return true;
    }
    
     public boolean delete(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert= "DELETE FROM `handshake`.`don` WHERE donId="+id;
        ste.executeUpdate(requeteInsert);
        System.out.println("Supression effectuer");
        return true;
    }
     
     public boolean update(DonEspeces t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    
    public boolean update(int id, String cible,int montant) throws SQLException {
         ste = con.createStatement();
        String requeteInsert= "UPDATE don SET donId='"+id
                +"',cibleDon='"+cible
                +"',montantDon="+montant
                +" WHERE donId="+id;
        ste.executeUpdate(requeteInsert);
        System.out.println("Modification effectuer");
        return true;
    }

    @Override
    public List<DonEspeces> readAll() throws SQLException {
        List<DonEspeces> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don where typeDon='Especes'");
       
     while (rs.next()) {  
       
              int id=rs.getInt(1);
              
               String cible=rs.getString("cibleDon");
               String type=rs.getString("typeDon");
               int montant=rs.getInt("montantDon");
                Date date = rs.getDate("dateDon");
                int user = rs.getInt("userId");
               DonEspeces de=new DonEspeces(id,montant, type, cible,user,date);
                
     arr.add(de);
         
          
     }
     
    return arr;
    
    }
    
    
    
    
    
}
