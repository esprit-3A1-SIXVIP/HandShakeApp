package Services;

import Entities.Article;
import Entities.stat;
import IServices.IService;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class ServiceArticle implements IService<Article> {
  private Connection con = DataBase.getInstance().getConnection();
  
  private Statement ste;
  
  PreparedStatement statement;
  
  public String ToString() {
    return "";
  }
  
  public static void main(String[] args) {
    System.out.println();
  }
  
  public List<Article> readAllarticle() throws SQLException {
    List<Article> l1 = new ArrayList<>();
    this.ste = this.con.createStatement();
    String requeteInsert = "select * from `handshake`.`article` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    while (rs.next()) {
      String titre = rs.getString("titre");
      String des = rs.getString("descriptionArticle");
      int id = rs.getInt(1);
      int uid = rs.getInt(5);
      Article a1 = new Article(id, titre, des, uid);
      l1.add(a1);
    } 
    return l1;
  }
  
  public void ajouter(Article t) throws SQLException {
    this.ste = this.con.createStatement();
    String requeteInsert = "INSERT INTO `handshake`.`article` (`auteur` ,`titre`, `descriptionArticle`,`img`) VALUES ( '" + t.getAuteur() + "','" + t.getTitre() + "', '" + t.getDescription() + "','" + t.getImage() + "');";
    int rowsUpdated = this.ste.executeUpdate(requeteInsert);
  }
  
  public boolean delete(Article t) throws SQLException {
    this.ste = this.con.createStatement();
    String requeteInsert = "delete  from `handshake`.`article`  where `articleId` = " + t.getId() + ";";
    int rowsUpdated = this.ste.executeUpdate(requeteInsert);
    return (rowsUpdated > 0);
  }
  
  public boolean deletetar() throws SQLException {
    this.ste = this.con.createStatement();
    String requeteInsert = "delete * from article ;";
    int rowsUpdated = this.ste.executeUpdate(requeteInsert);
    return (rowsUpdated > 0);
  }
  
  public boolean update(Article t) throws SQLException {
    String sql = "UPDATE article SET titre=?, descriptionArticle=?, auteur=? WHERE articleId=?";
    this.statement = (PreparedStatement)this.con.prepareStatement(sql);
    this.statement.setString(1, t.getTitre());
    this.statement.setString(2, t.getDescription());
    this.statement.setInt(3, t.getId());
    this.statement.setString(4, t.getAuteur());
    int rowsUpdated = this.statement.executeUpdate();
    return (rowsUpdated > 0);
  }
  
  public ResultSet readAlldon() throws SQLException {
    this.ste = this.con.createStatement();
    String requeteInsert = "select count(*) as cnt from `handshake`.`article` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    int id = 0;
    return rs;
  }
  
  public ResultSet readAllenement() throws SQLException {
    this.ste = this.con.createStatement();
    int id = 0;
    String requeteInsert = "select count(*) as cnt from `handshake`.`evenement` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    return rs;
  }
  
  public stat readAllaide() throws SQLException {
    this.ste = this.con.createStatement();
    int id = 0;
    String requeteInsert = "select count(*) as cnt from `handshake`.`aide` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    stat s1 = null;
    while (rs.next())
      id = rs.getInt("cnt"); 
    s1 = new stat(id);
    return s1;
  }
  
  public Article findid(Integer i) throws SQLException {
    this.ste = this.con.createStatement();
    String requeteInsert = "select * from `handshake`.`article`  where `articleId` = " + i + ";";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    Article a1 = null;
    while (rs.next()) {
      String auth = rs.getString("auteur");
      String titre = rs.getString("titre");
      String des = rs.getString("descriptionArticle");
      String img = rs.getString("img");
      int id = rs.getInt("articleId");
      int uid = rs.getInt("id");
      a1 = new Article(id, auth, titre, des, img, uid);
    } 
    return a1;
  }
  
  public List<Integer> readAllid() throws SQLException {
    List<Integer> l1 = new ArrayList<>();
    this.ste = this.con.createStatement();
    String requeteInsert = "select articleid from `handshake`.`article` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    while (rs.next()) {
      int id = rs.getInt(1);
      l1.add(Integer.valueOf(id));
    } 
    return l1;
  }
  
  public Integer getlid() throws SQLException {
    List<Integer> l1 = new ArrayList<>();
    this.ste = this.con.createStatement();
    String requeteInsert = "select articleid from `handshake`.`article` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    while (rs.next()) {
      int i = rs.getInt(1);
      l1.add(Integer.valueOf(i));
    } 
    int id = ((Integer)l1.get(l1.size() - 1)).intValue() + 1;
    return Integer.valueOf(id);
  }
  
  public int readAllid1() throws SQLException {
    List<Integer> l1 = new ArrayList<>();
    this.ste = this.con.createStatement();
    int id = 0;
    String requeteInsert = "select * from `handshake`.`article` ;";
    ResultSet rs = this.ste.executeQuery(requeteInsert);
    while (rs.next())
      id = rs.getInt(1); 
    return id;
  }
  
  public ObservableList<Article> readAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public stat readAllbenef() {
    stat s1 = null;
    try {
      try {
        this.ste = this.con.createStatement();
      } catch (SQLException ex) {
        Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, (String)null, ex);
      } 
      int id = 0;
      String requeteInsert = "select count(*) as cnt from `handshake`.`beneficiaire` ;";
      ResultSet rs = this.ste.executeQuery(requeteInsert);
      while (rs.next())
        id = rs.getInt("cnt"); 
      s1 = new stat(id);
      return s1;
    } catch (SQLException ex) {
      Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, (String)null, ex);
      return s1;
    } 
  }
}

