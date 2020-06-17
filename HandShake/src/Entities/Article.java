package Entities;

public class Article {
  private int id;
  
  private String Auteur;
  
  private String titre;
  
  private String descriptionArticle;
  
  private String image;
  
  private int userId;
  
  User user;
  
  public Article(int id, String auth, String titre, String des, String img, int uid) {
    this.Auteur = auth;
    this.titre = titre;
    this.descriptionArticle = des;
    this.image = img;
    this.userId = uid;
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
  
  public int getid() {
    return this.user.getid();
  }
  
  public Article(String titre, String description, int id) {
    this.titre = titre;
    this.descriptionArticle = description;
    this.id = this.user.getid();
    this.id = id;
  }
  
  public Article(int id, String titre, String descriptionArticle, int userId) {
    this.id = id;
    this.titre = titre;
    this.descriptionArticle = descriptionArticle;
    this.userId = userId;
  }
  
  public String toString() {
    return "Article{id=" + this.id + ", Auteur=" + this.Auteur + ", titre=" + this.titre + ", descriptionArticle=" + this.descriptionArticle + ", image=" + this.image + ", userId=" + this.userId + ", user=" + this.user.nomUser + '}';
  }
}
