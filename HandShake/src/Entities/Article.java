package Entities;

public class Article {
  private int id;
  
  private String Auteur;
  
  private String titre;
  
  private String descriptionArticle;
  
  private String image;
  
  private int userid;
  
  User user;
  
  public Article(int id, String auth, String titre, String des, String img, int uid) {
    this.Auteur = auth;
    this.titre = titre;
    this.descriptionArticle = des;
    this.image = img;
    this.userid = uid;
  }
  
  public String getAuteur() {
    return this.Auteur;
  }
  
  public void setAuteur(String Auteur) {
    this.Auteur = Auteur;
  }
  
  public String getDescriptionArticle() {
    return this.descriptionArticle;
  }
  
  public void setDescriptionArticle(String descriptionArticle) {
    this.descriptionArticle = descriptionArticle;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getTitre() {
    return this.titre;
  }
  
  public void setTitre(String titre) {
    this.titre = titre;
  }
  
  public String getDescription() {
    return this.descriptionArticle;
  }
  
  public void setDescription(String description) {
    this.descriptionArticle = description;
  }
  
  public int getUserid() {
    return this.user.getUserId();
  }
  
  public Article(String titre, String description, int userid) {
    this.titre = titre;
    this.descriptionArticle = description;
    this.userid = this.user.getUserId();
    this.userid = userid;
  }
  
  public Article(int id, String titre, String descriptionArticle, int userid) {
    this.id = id;
    this.titre = titre;
    this.descriptionArticle = descriptionArticle;
    this.userid = userid;
  }
  
  public String toString() {
    return "Article{id=" + this.id + ", Auteur=" + this.Auteur + ", titre=" + this.titre + ", descriptionArticle=" + this.descriptionArticle + ", image=" + this.image + ", userid=" + this.userid + ", user=" + this.user.nomUser + '}';
  }
}
