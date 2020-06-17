/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DonEspeces;
import Entities.DonNature;
import Entities.User;
import IServices.InterfaceDon;
import Utils.DataBase;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steph
 */
public class ServiceDonNature implements InterfaceDon<DonNature> {
    
    private Connection con;
    private Statement ste;

    public ServiceDonNature() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    
    @Override
    public void ajouter(DonNature t) throws SQLException {
         long  millis = System.currentTimeMillis ();  
    java.sql.Date date = new  java.sql.Date (millis);
         PreparedStatement pre=con.prepareStatement("INSERT INTO `handshake`.`don` ( `libelleDonNature`, `categorieDonNature`, `quantiteDonNature`,`typeDon`,`cibleDon`,`id`,`dateDon`) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, t.getLibelleDonNature());
    pre.setString(2, t.getCategorieDonNature());
    pre.setInt(3,t.getQuantiteDonNature());
    pre.setString(4,"Nature");
    pre.setString(5, t.getCibleDon());
    pre.setInt(6,t.getid());
    pre.setDate(7,date );
    pre.executeUpdate();
    }

    @Override
    public boolean delete(DonNature t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert= "DELETE FROM `handshake`.`don` WHERE donId="+t.getDonId();
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
     public int updateid(String cible, String libelle,String categorie,int quantite) throws SQLException
     {
         ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don where cibleDon='" + cible + "' and  libelleDonNature='" + libelle + "' and categorieDonNature='"+categorie +"' and quantiteDonNature="+quantite);
        if (rs.next()) {
            return rs.getInt("donId");
        }

        return -1;
     }
    
    public boolean update(int id,String cible, String libelle,String categorie,int quantite) throws SQLException {
         ste = con.createStatement();
        String requeteInsert= "UPDATE don SET donId='"+id
                +"',cibleDon='"+cible
                +"',libelleDonNature='"+libelle
                +"',categorieDonNature='"+categorie
                +"',quantiteDonNature="+quantite
                +" WHERE donId="+id;
        ste.executeUpdate(requeteInsert);
        System.out.println("Modification effectuer");
        return true;
    }

    @Override
    public List<DonNature> readAll() throws SQLException {
        List<DonNature> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don where typeDon='Nature'");
       
     while (rs.next()) {  
         
             int id=rs.getInt("donId");
             int user = rs.getInt("id");
             String libelle=rs.getString("libelleDonNature");
             String type=rs.getString("typeDon");
             String categorie=rs.getString("categorieDonNature");
             int quantite=rs.getInt("quantiteDonNature");
             String cible=rs.getString("cibleDon");
             java.sql.Date date1 = java.sql.Date.valueOf(rs.getString("dateDon")) ;
             DonNature dn=new DonNature(libelle, categorie, quantite, id, type, cible,date1,user);
             
             arr.add(dn);
         
          
     }
     
    return arr;
    
    }
    
    public double moyenneA() throws SQLException
    {
        double i=0 , j=0;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from don where typeDon='Nature'");
        
        while(rs.next())
        {
            if( rs.getString("CategorieDonNature").equals("Alimentaire"))
            {
                i += rs.getInt("quantiteDonNature");
                j++;
            }
        }
        if(j !=0)
        {
             return i/j;
        }
       return 0;
    }
    
    public double moyenneV() throws SQLException
    {
        double i=0 , j=0;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from don where typeDon='Nature'");
        
        while(rs.next())
        {
            if( rs.getString("CategorieDonNature").equals("Vestimentaire"))
            {
                i += rs.getInt("quantiteDonNature");
                j++;
            }
        }
        if(j !=0)
        {
             return i/j;
        }
       return 0;
    }
    
    public double moyenneAutre() throws SQLException
    {
        double i=0 , j=0;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from don where typeDon='Nature'");
        
        while(rs.next())
        {
            if( rs.getString("CategorieDonNature").equals("Vestimentaire") != true && rs.getString("CategorieDonNature").equals("Alimentaire") != true )
            {
                i += rs.getInt("quantiteDonNature");
                j++;
            }
        }
        if(j !=0)
        {
             return i/j;
        }
       return 0;
    }

    @Override
    public boolean update(DonNature t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
