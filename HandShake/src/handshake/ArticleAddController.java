package handshake;

import Entities.Article;
import Entities.stat;
import Services.ServiceArticle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.objects.NativeString;

public class ArticleAddController implements Initializable {
  @FXML
  private AnchorPane p1;
  
  @FXML
  private ImageView drag;
  
  @FXML
  private JFXTextArea desc;
  
  @FXML
  private JFXTextField titre;
  
  @FXML
  private JFXTextField msg;
  
  @FXML
  private JFXTextField auth;
  
  public Image image = null;
  
  static Integer i;
  
  @FXML
  private AnchorPane prin;
  
  @FXML
  private ImageView imgrot;
  
  @FXML
  private JFXButton btnaffsc2;
  
  @FXML
  private JFXButton btnaffsc3;
  
  
  public Article ar;
    private SubScene sc;
    private Pane btnhover;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (this.ar != null)
      System.out.println(this.ar.getTitre()); 
    
    
      Timeline rotate = new Timeline();
      DoubleProperty r = this.imgrot.rotateProperty();
      rotate.getKeyFrames().addAll(new KeyFrame[] { new KeyFrame(new Duration(0), new KeyValue[] { new KeyValue(r, 
                  Integer.valueOf(0)) }), new KeyFrame(new Duration(23000.0D), new KeyValue[] { new KeyValue(r, 
                  Integer.valueOf(-360)) }), new KeyFrame(new Duration(23000.0D), new KeyValue[] { new KeyValue(r, Integer.valueOf(0)) }), new KeyFrame(new Duration(46000.0D), new KeyValue[] { new KeyValue(r, 
                  Integer.valueOf(-360)) }) });
      rotate.play();
//    btnaffsc3.addEventHandler(MouseEvent.MOUSE_ENTERED,
//        new EventHandler<MouseEvent>() {
//          @Override
//          public void handle(MouseEvent e) {
//              showstat(new ActionEvent());
//         sc.setHeight(400);
//        sc.setWidth(400);   
//          
//          
//          
//           
//          }});
//          
//          sc.addEventHandler(MouseEvent.MOUSE_CLICKED,
//        new EventHandler<MouseEvent>() {
//          @Override
//          public void handle(MouseEvent e) {
//              showstat(new ActionEvent());
//        sc.setHeight(0);
//        sc.setWidth(0);   
//          
//           
//          }
//        });
 
    
    
  }
  @FXML
  private void drov(DragEvent event) {
    if (event.getDragboard().hasFiles())
      event.acceptTransferModes(TransferMode.ANY); 
  }
  @FXML
  private void dradrop(DragEvent event) {
    List<File> files = event.getDragboard().getFiles();
    Image img = null;
    try {
      img = new Image(new FileInputStream(files.get(0)));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    this.drag.setImage(img);
    this.image = img;
  }
  @FXML
  private void addfrm(MouseEvent event) {
    try {
      AnchorPane pane = FXMLLoader.<AnchorPane>load(getClass().getResource("articleAdmin.fxml"));
      this.p1.getChildren().setAll(new Node[] { pane });
    } catch (IOException ex) {
      ex.printStackTrace();
    } 
  }
  
  public static void saveToFile(Image image) {
    File outputFile = new File("C:/xampp/htdocs/javafx/" + i + ".png");
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
      ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
  }
  
  @FXML
  private void save(MouseEvent event) {
    if (NativeString.trim(this.titre.getText()).equals("")) {
      this.msg.setText("veuiller inserer un titre");
      return;
    } 
    if (NativeString.trim(this.desc.getText()).equals("")) {
      this.msg.setText("veuiller inserer la description");
      return;
    } 
    if (NativeString.trim(this.auth.getText()).equals("")) {
      this.msg.setText("veuiller inserer un auteur");
      return;
    } 
    ServiceArticle ser = new ServiceArticle();
    if (this.image != null) {
      try {
        i = ser.getlid();
      } catch (SQLException ex) {
        Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, (String)null, ex);
      } 
      saveToFile(this.image);
    } 
    Article a1 = null;
    String v3 = this.auth.getText();
    String v1 = this.titre.getText();
    String v2 = this.desc.getText();
    String v4 = i + ".png";
    a1 = new Article(1, v3, v1, v2, v4, 1);
    try {
      if (this.ar == null) {
        ser.ajouter(a1);
      } else {
        a1.setId(this.ar.getId());
        ser.update(a1);
      } 
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    } 
    this.msg.setText("sauvgarde avec succes");
    this.auth.setText(null);
    this.desc.setText(null);
    this.titre.setText(null);
    this.drag.setImage(null);
    this.ar = null;
  }
  
  @FXML
  private void quit(MouseEvent event) {
    this.msg.setText("");
    this.auth.setText(null);
    this.desc.setText(null);
    this.titre.setText(null);
    this.drag.setImage(null);
    try {
      FXMLLoader pane = new FXMLLoader(getClass().getResource("articleAdmin.fxml"));
      Parent root = pane.<Parent>load();
      ArticleAdminController dpc = pane.<ArticleAdminController>getController();
      this.p1.getScene().setRoot(root);
    } catch (IOException ex) {
      ex.printStackTrace();
    } 
  }
  
  void setDet(Article a11) {
    this.ar = a11;
    this.msg.setText("");
    this.auth.setText(this.ar.getAuteur());
    this.desc.setText(this.ar.getDescription());
    this.titre.setText(this.ar.getTitre());
    File file = new File("C:/xampp/htdocs/javafx/" + this.ar.getId() + ".png");
    String localUrl = null;
    try {
      localUrl = file.toURI().toURL().toString();
    } catch (MalformedURLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    Image localImage = new Image(localUrl, false);
    this.drag.setImage(localImage);
  }


  
  
    @FXML
    private void showstat(ActionEvent event) {
         try {
             System.out.println(event.toString());    
           final PieChart chart = new PieChart(); 
        chart.setTitle("Stock de fruits");  
      ServiceArticle ser = new ServiceArticle();
      stat readAllaide = ser.readAllaide();
      stat readAlle = ser.readAllbenef();
      chart.setTitle("aide et don");
      int v1 = readAllaide.getInt1();
      int v2 = ser.readAllid1();
      chart.getData().setAll(new PieChart.Data[] { new PieChart.Data("aide", v1), new PieChart.Data("don", v2) });
      Button b1 =new Button();
      AnchorPane root = new AnchorPane(chart,b1);
      sc.getScene().setRoot(root);
            
    } catch (SQLException ex) {
      Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
        
        
        
    }


}
