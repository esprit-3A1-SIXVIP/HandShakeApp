/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;

import Entities.Question;
import Entities.User;
import IServices.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;


/**
 *
 * @author House
 */
public class CommentaireService implements IService<Commentaire> {

    private final Connection con;
    private Statement ste;

    public CommentaireService() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Commentaire t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `handshake`.`commentaire` (`userId`, `questionId`, `texteCommentaire`, `dateCommentaire`) VALUES ('" + t.getUser().getUserId() + "', '" + t.getQuestion().getQuestionId() + "', '" + t.getTexteCommentaire() + "', '" +t.getDateCommentaire()+"');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Commentaire p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `handshake`.`commentaire` (`userId`, `questionId`, `texteCommentaire`, `dateCommentaire`) VALUES ( ?, ?, ?, ?);");
    pre.setInt(1, p.getUser().getUserId());
    pre.setInt(2, p.getQuestion().getQuestionId());
    pre.setString(3, p.getTexteCommentaire());
    pre.setDate(4, p.getDateCommentaire());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Commentaire t) throws SQLException {
       ste = con.createStatement();
       String requeteDelete = "DELETE FROM `handshake`.`commentaire` WHERE `userId`= '" + t.getUser().getUserId() + "' AND `questionId`= '" + t.getQuestion().getQuestionId() + "';";
       return(ste.execute(requeteDelete));
    }

    @Override
    public boolean update(Commentaire t) throws SQLException {
       ste = con.createStatement();
       String requeteUpdate = "UPDATE `handshake`.`commentaire` SET `texteCommentaire` = '" + t.getTexteCommentaire() + "', `dateCommentaire` = '" +t.getDateCommentaire()+"' WHERE `userId`= '" + t.getUser().getUserId() + "' AND `questionId`= '" + t.getQuestion().getQuestionId() + "';";
       return(ste.execute(requeteUpdate)); 
    }
    public List<Commentaire> readAll(Question Q) throws SQLException {
    List<Commentaire> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select u.userId,q.questionId,c.texteCommentaire,c.dateCommentaire from commentaire c join question q join user u where q.questionId='"+Q.getQuestionId()+"' and u.userId='"+Q.getUser().getUserId()+"'");
     while (rs.next()) {
               String texteCommentaire=rs.getString("texteCommentaire");
               Date dateCommentaire=rs.getDate("dateCommentaire");
               Commentaire p=new Commentaire(Q.getUser(), Q, texteCommentaire, dateCommentaire);               
               arr.add(p);
     }
    return arr;
    }
    public List<Commentaire> search(String S,Question Q) throws SQLException {
     List<Commentaire> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select u.userId,q.questionId,c.texteCommentaire,c.dateCommentaire from commentaire c join question q join user u where q.questionId='"+Q.getQuestionId()+"' and u.userId='"+Q.getUser().getUserId()+"' and 'texteCommentaire' like '%"+S+"%';");
     while (rs.next()) {                
               String texteCommentaire=rs.getString("texteCommentaire");
               Date dateCommentaire=rs.getDate("dateCommentaire");
               Commentaire p=new Commentaire(Q.getUser(), Q, texteCommentaire, dateCommentaire);                
               arr.add(p);
     }
    return arr;
    }   

    @Override
    public List<Commentaire> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
