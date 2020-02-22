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
    public class QuestionService implements IService<Question> {

    private final Connection con;
    private Statement ste;

    public QuestionService() {
        con = DataBase.getInstance().getConnection();

    }
    public int countDailyQuestionsByUser(List<Question> questionList, Question t) {
        int count = 0;
        return (questionList.stream().filter((q) -> (((q.getUser().getUserId())==(t.getUser().getUserId()))&&(q.getDateQuestion().getDay()==t.getDateQuestion().getDay()))).map((_item) -> {
            return 1;
        }).reduce(count, Integer::sum));
    }
    public boolean checkQuestionHasComments(List<Commentaire> commentList, Question t) {
        return (commentList.stream().anyMatch((c) -> ((c.getUser().getUserId())==(t.getUser().getUserId()))));
    }
    @Override
    public List<Question> readAll() throws SQLException {
        List<Question> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select Q.questionId,Q.texteQuestion,Q.dateQuestion,U.userId,U.login,U.password,U.email,U.role from question Q join user U where Q.userId=U.userId");
         while (rs.next()) {                
                   int questionId=rs.getInt("questionId");
                   String texteQuestion=rs.getString("texteQuestion");
                   Date dateQuestion=rs.getDate("dateQuestion");
                   User user=new User(rs.getInt("userId"),rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getString("role"));
                   Question p=new Question(questionId, texteQuestion, dateQuestion,user);
         arr.add(p);
         }
        return arr;
    }
    
    @Override
    public void ajouter(Question t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `handshake`.`question` (`questionId`, `texteQuestion`, `dateQuestion`,`userId`) VALUES (NULL, '" + t.getTexteQuestion() + "', '" +t.getDateQuestion()+"','" + t.getUser().getUserId() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Question p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `handshake`.`question` (`questionId`, `texteQuestion`, `dateQuestion`,`userId`) VALUES ( NULL, ?, ?, ?);");
    pre.setInt(4, p.getUser().getUserId());
    pre.setString(2, p.getTexteQuestion());
    pre.setDate(3, p.getDateQuestion());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Question t) throws SQLException {
       CommentaireService CDB= new CommentaireService();
       if (this.checkQuestionHasComments(CDB.readAll(), t)){   
       ste = con.createStatement();
       String requeteDelete = "DELETE FROM `handshake`.`question` WHERE `questionId`= '" + t.getQuestionId() + "' AND`userId`= '" + t.getUser().getUserId() + "';";
       return(ste.execute(requeteDelete));
       }
       else {
           System.out.println("Vous ne pouvez pas supprimer cette question car elle a des commentaires associés.");
           return false;
    }
    }
    @Override
    public boolean update(Question t) throws SQLException {
       ste = con.createStatement();
       String requeteUpdate = "UPDATE `handshake`.`question` SET `texteQuestion` = '" + t.getTexteQuestion() + "' WHERE `questionId`= '" + t.getQuestionId() + "' AND`userId`= '" + t.getUser().getUserId() + "';";
       return(ste.execute(requeteUpdate)); 
    }
    public List<Question> search(String S) throws SQLException {
     List<Question> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select Q.questionId,Q.texteQuestion,Q.dateQuestion,U.userId,U.login,U.password,U.email,U.role from question Q join user U where Q.userId=U.userId and `texteQuestion` like '%"+S+"%';");
     while (rs.next()) {                
               int questionId=rs.getInt("questionId");
               String texteQuestion=rs.getString("texteQuestion");
               Date dateQuestion=rs.getDate("dateQuestion");
               User user=new User(rs.getInt("userId"),rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getString("role"));
               Question p=new Question(questionId, texteQuestion, dateQuestion,user);
     arr.add(p);
     }
    return arr;
    }   
}

