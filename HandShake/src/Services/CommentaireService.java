
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
       String requeteDelete = "DELETE FROM `handshake`.`commentaire` WHERE `userId`= '" + t.getUser().getUserId() + "' AND `questionId`= '" + t.getQuestion().getQuestionId() + "' AND `dateCommentaire`='"+t.getDateCommentaire()+"';";
       return(ste.execute(requeteDelete));
    }

    @Override
    public boolean update(Commentaire t) throws SQLException {
       ste = con.createStatement();
       String requeteUpdate = "UPDATE `handshake`.`commentaire` SET `texteCommentaire` = '" + t.getTexteCommentaire().replaceAll("'", "`") + "', score="+t.getScore()+" WHERE `userId`= '" + t.getUser().getUserId() + "' AND `questionId`= '" + t.getQuestion().getQuestionId() + "';";
       return(ste.execute(requeteUpdate)); 
    }
    
    public ObservableList<Commentaire> readAll(Question Q) throws SQLException {
    ObservableList<Commentaire> arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ServiceUser us=new ServiceUser();
    ResultSet rs=ste.executeQuery("select c.userId,c.questionId,c.texteCommentaire,c.dateCommentaire,c.score from commentaire c join question q where c.questionId=q.questionId and q.questionId="+Q.getQuestionId());
     while (rs.next()) {
               String texteCommentaire=rs.getString("texteCommentaire");
               Date dateCommentaire=rs.getDate("dateCommentaire");
               int userId= rs.getInt("userId");  
               int score=rs.getInt("score");
               Commentaire p=new Commentaire(us.getUser(userId), Q, texteCommentaire, dateCommentaire, score);               
               arr.add(p);
     }
     return arr;
     }
    public int countComments(Question Q) throws SQLException {
        int C=0;
        ste=con.createStatement();
        ResultSet rs= ste.executeQuery("Select count(*) as c from commentaire where questionId="+Q.getQuestionId());
        if (rs.next())
            C=rs.getInt("c");
        return C;
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
    public ObservableList<Commentaire> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
