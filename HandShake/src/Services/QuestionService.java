
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
    public class QuestionService implements IService<Question> {

    private final Connection con;
    private Statement ste;

    public QuestionService() {
        con = DataBase.getInstance().getConnection();

    }
    public int countDailyQuestionsByUser(List<Question> questionList, Question t) {
        int count = 0;
        return (questionList.stream().filter((q) -> (((q.getUser().getid())==(t.getUser().getid()))&&(q.getDateQuestion().getDay()==t.getDateQuestion().getDay()))).map((_item) -> {
            return 1;
        }).reduce(count, Integer::sum));
    }
    public boolean checkQuestionHasComments(List<Commentaire> commentList, Question t) {
        return (commentList.stream().anyMatch((c) -> ((c.getUser().getid())==(t.getUser().getid()))));
    }
    
    public ObservableList<Question> readAll() throws SQLException {
        ObservableList<Question> arr=FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select Q.questionId,Q.texteQuestion,Q.dateQuestion,Q.score,U.id,U.username,U.password,U.email,U.roles from question Q join user U where Q.id=U.id");
         while (rs.next()) {                
                   int questionId=rs.getInt("questionId");
                   String texteQuestion=rs.getString("texteQuestion");
                   Date dateQuestion=rs.getDate("dateQuestion");
                   int score=rs.getInt("score");
                   User user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("roles"));
                   Question p=new Question(questionId, texteQuestion, dateQuestion,score,user);
         arr.add(p);
         }
        return arr;
    }
    
    @Override
    public void ajouter(Question t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `handshake`.`question` (`questionId`, `texteQuestion`, `dateQuestion`,`id`) VALUES (NULL, '" + t.getTexteQuestion() + "', '" +t.getDateQuestion()+"'," + t.getUser().getid() + ");";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Question p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `handshake`.`question` (`questionId`, `texteQuestion`, `dateQuestion`,`id`) VALUES ( NULL, ?, ?, ?);");
    pre.setInt(4, p.getUser().getid());
    pre.setString(2, p.getTexteQuestion());
    pre.setDate(3, p.getDateQuestion());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Question t) throws SQLException {
       CommentaireService CDB= new CommentaireService();
         
       ste = con.createStatement();
       String requeteDelete = "DELETE FROM `handshake`.`question` WHERE `questionId`= '" + t.getQuestionId() + "' AND`id`= '" + t.getUser().getid() + "';";
       return(ste.execute(requeteDelete));
    }
    @Override
    public boolean update(Question t) throws SQLException {
       ste = con.createStatement();
       String requeteUpdate = "UPDATE `handshake`.`question` SET `texteQuestion` = '" + t.getTexteQuestion().replaceAll("'", "`") + "', score="+t.getScore()+" WHERE `questionId`= '" + t.getQuestionId() + "' AND`id`= '" + t.getUser().getid() + "';";
       return(ste.execute(requeteUpdate)); 
    }
    public ObservableList<Question> search(String S) throws SQLException {
     ObservableList<Question> arr=FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select Q.questionId,Q.texteQuestion,Q.dateQuestion,U.id,U.username,U.password,U.email,U.roles,U.accesShakeHub from question Q join user U where Q.id=U.id and (`texteQuestion` like '%"+S+"%' or `U.username` like '%"+S+"%');");
     while (rs.next()) {                
               int questionId=rs.getInt("questionId");
               String texteQuestion=rs.getString("texteQuestion");
               Date dateQuestion=rs.getDate("dateQuestion");
               User user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("roles"),rs.getInt("accesShakeHub"));
               Question p=new Question(questionId, texteQuestion, dateQuestion,user);
     arr.add(p);
     }
    return arr;
    }
    public Question getQuestion(int id) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from question where questionId=" + id );
        ServiceUser us =new ServiceUser();
        if (rs.next()) {
            Question Q = new Question(rs.getInt("questionId"),rs.getString("texteQuestion"),rs.getDate("dateQuestion"),rs.getInt("score"),us.getUser(rs.getInt("id")));
            return Q;
        }

        return null;
    }
}
