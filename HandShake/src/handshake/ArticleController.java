package handshake;

import Entities.Article;
import Entities.stat;
import Services.ServiceArticle;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ArticleController implements Initializable {
  private PieChart typ1;
  
  private Text txttitre;
  
  public Article ar;
  
  private Text ajoutAr;
  
  private SubScene scene2;
  
  private ScrollPane listpane;
  
  private ListView<Integer> liste1;
  
  Integer[] fonts = new Integer[0];
  
  private AnchorPane page;
  
  @FXML
  private AnchorPane p1;
  
  @FXML
  private Pagination pagination;
  
  @FXML
  private ImageView imgrot;
  
  private ImageView drag;
  
  @FXML
  private JFXButton btnaffsc2;
  
  @FXML
  private JFXButton btnaffsc3;
  
  @FXML
  private JFXButton btnaffsc1;
  
  public List<Integer> list = null;
  
  @FXML
  private AnchorPane prin;
  
  @FXML
  private JFXButton btnaffsc31;
  
  @FXML
  private JFXButton btnaffsc32;
  
  @FXML
  private PieChart chart;
  
  private JFXButton btnart;
  
  public int itemsPerPage() {
    return 15;
  }
  
  void setDetail(String string) {
    this.txttitre.setText(string);
  }
  
  public VBox createPage(int pageIndex) {
    VBox box = new VBox(5.0D);
    int page = pageIndex * itemsPerPage();
    for (int i = page; i < page + itemsPerPage(); i++) {
      Label font = new Label("");
      box.getChildren().add(font);
    } 
    return box;
  }
  
  public void initialize(URL location, ResourceBundle resources) {
    for (int i = 0; i < 10; i++) {
      Timeline rotate = new Timeline();
      DoubleProperty r = this.imgrot.rotateProperty();
      rotate.getKeyFrames().addAll(new KeyFrame[] { new KeyFrame(new Duration(0.0D), new KeyValue[] { new KeyValue(r, 
                  (T)Integer.valueOf(0)) }), new KeyFrame(new Duration(23000.0D), new KeyValue[] { new KeyValue(r, 
                  (T)Integer.valueOf(-360)) }), new KeyFrame(new Duration(23000.0D), new KeyValue[] { new KeyValue(r, (T)Integer.valueOf(0)) }), new KeyFrame(new Duration(46000.0D), new KeyValue[] { new KeyValue(r, 
                  (T)Integer.valueOf(-360)) }) });
      rotate.play();
    } 
    charger(new ActionEvent());
  }
  
  private void charger(ActionEvent event) {
    this.list = null;
    ServiceArticle ser = new ServiceArticle();
    try {
      this.list = ser.readAllid();
    } catch (SQLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    List<Integer> list11 = this.list;
    this.pagination.setPageCount(this.list.size());
    this.pagination.setCurrentPageIndex(0);
    this.pagination.setMaxPageIndicatorCount(3);
    this.pagination.setPageFactory(pageIndex -> {
          Integer i1 = Integer.valueOf(this.pagination.getCurrentPageIndex());
          Article a1 = null;
          try {
            a1 = ser.findid(list11.get(i1.intValue()));
          } catch (SQLException ex) {
            System.out.println("nbr");
          } 
          this.ar = a1;
          this.ar.setId(((Integer)list11.get(i1.intValue())).intValue());
          Label label1 = new Label("                      " + a1.getTitre());
          label1.setFont(new Font("Verdana", 36.0D));
          label1.setTextFill(Color.CORAL);
          Label label2 = new Label(a1.getDescription());
          label2.setFont(new Font("Arial", 24.0D));
          Label sep = new Label("                       ");
          Label sep1 = new Label("                       ");
          Label sep2 = new Label("                       ");
          Label label3 = new Label("   par   " + a1.getAuteur());
          label3.setFont(new Font("Arial", 16.0D));
          label3.setTextFill(Color.BROWN);
          File file = new File("C:/xampp/htdocs/javafx/" + list11.get(i1.intValue()) + ".png");
          String localUrl = null;
          try {
            localUrl = file.toURI().toURL().toString();
          } catch (MalformedURLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
          Image localImage = new Image(localUrl, false);
          ImageView im = new ImageView(localImage);
          Pane pim = new Pane(new Node[] { im });
          im.setFitHeight(350.0D);
          im.setFitWidth(400.0D);
          im.preserveRatioProperty();
          im.setX(100.0D);
          String ss = a1.getImage();
          BorderPane bp = new BorderPane();
          bp.setTop(label1);
          double d1 = localImage.getWidth();
          if (d1 != 0.0D) {
            bp.setCenter(new VBox(new Node[] { sep, pim, sep1, label2, sep2, label3 }));
          } else {
            bp.setCenter(new VBox(new Node[] { sep, label2, sep1, label3 }));
          } 
          return new VBox(new Node[] { bp });
        });
    ScrollPane vBox = new ScrollPane(this.pagination);
    vBox.setLayoutX(88.0D);
    vBox.setLayoutY(51.0D);
    this.p1.getChildren().add(vBox);
  }
  
  private void drov(DragEvent event) {
    if (event.getDragboard().hasImage())
      event.acceptTransferModes(TransferMode.ANY); 
  }
  
  private void dradrop(DragEvent event) {
    if (event.getDragboard().hasImage())
      event.acceptTransferModes(TransferMode.ANY); 
    Image img = event.getDragboard().getImage();
    this.drag.setImage(img);
  }
  
  @FXML
  private void retour(MouseEvent event) {
    try {
      FXMLLoader top = new FXMLLoader(getClass().getResource("Home.fxml"));
      Parent root = top.<Parent>load();
      HomeController dpc = top.<HomeController>getController();
      this.p1.getScene().setRoot(root);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    } 
  }
  
  @FXML
  private void addfrm(MouseEvent event) {
    try {
      FXMLLoader top = new FXMLLoader(getClass().getResource("articleadd.fxml"));
      Parent root = top.<Parent>load();
      ArticleAddController dpc = top.<ArticleAddController>getController();
      this.p1.getScene().setRoot(root);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    } 
  }
  
  @FXML
  private void modifier(MouseEvent event) {
    Integer i1 = Integer.valueOf(this.pagination.getCurrentPageIndex());
    ServiceArticle ser = new ServiceArticle();
    Article a1 = null;
    try {
      a1 = ser.findid(this.list.get(i1.intValue()));
    } catch (SQLException ex) {
      System.out.println("nbr");
    } 
    try {
      FXMLLoader top = new FXMLLoader(getClass().getResource("articleadd.fxml"));
      Parent root1 = top.<Parent>load();
      ArticleAddController dpc = top.<ArticleAddController>getController();
      a1.setId(((Integer)this.list.get(i1.intValue())).intValue());
      dpc.setDet(a1);
      this.p1.getScene().setRoot(root1);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    } 
  }
  
  @FXML
  private void delete(MouseEvent event) {
    ServiceArticle ser = new ServiceArticle();
    try {
      ser.delete(this.ar);
    } catch (SQLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    FXMLLoader top = new FXMLLoader(getClass().getResource("article.fxml"));
    Parent root1 = null;
    try {
      root1 = top.<Parent>load();
    } catch (IOException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    ArticleController dpc = top.<ArticleController>getController();
    this.p1.getScene().setRoot(root1);
  }
  
  @FXML
  private void mkpdf() {
    Integer i1 = Integer.valueOf(this.pagination.getCurrentPageIndex());
    ServiceArticle ser = new ServiceArticle();
    Article a1 = null;
    try {
      a1 = ser.findid(this.list.get(i1.intValue()));
    } catch (SQLException ex) {
      System.out.println("nbr");
    } 
    Pdfcreat p1 = new Pdfcreat();
    try {
      p1.add("article.pdf", a1.getTitre(), a1.getDescription(), a1.getAuteur());
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (DocumentException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
  }
  
  private void showstat(MouseEvent event) {
    try {
      ServiceArticle ser = new ServiceArticle();
      stat readAllaide = ser.readAllaide();
      stat readAlle = ser.readAllbenef();
      this.chart.setTitle("aide et don");
      int v1 = readAllaide.getInt1();
      int v2 = ser.readAllid1();
      this.chart.getData().setAll(new PieChart.Data[] { new PieChart.Data("aide", v1), new PieChart.Data("don", v2) });
    } catch (SQLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  private void affart(MouseEvent event) {
    this.btnart.setVisible(false);
  }
}
