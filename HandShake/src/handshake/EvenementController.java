/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Services.ServiceEvenement;
import Services.ServiceUser;
import Utils.UserSession;
import com.itextpdf.text.Image;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class EvenementController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtnom;
    @FXML
    private JFXTextField txtlieu;
    @FXML
    private JFXTextField txtprix;
    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXTimePicker timeheure;
    @FXML
    private JFXButton btnCreer;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXTextField txtportee;
    @FXML
    private JFXButton btnStat;
    @FXML
    private JFXButton browse;
    private ImageView imageView;
    private FileInputStream fis;
    private File file;
    @FXML
    private JFXTextField image;
    private final Desktop desktop=Desktop.getDesktop();
    //private JFXTextArea textArea;
    @FXML
    private JFXButton btn3;
     private JFXTextField fichier = null;
    public   String fich;
    @FXML
    private ImageView home;
  
    
    


    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
       
     FileChooser f = new FileChooser();
    
   browse.setOnAction( e->{
    File file = f.showOpenDialog(null);
       if(file!=null){
         image.setText(file.getAbsolutePath());
         fich = image.getText();
      
         
  

       }
   }
   );
  
       
    }    
    


    @FXML
    private void goToMesEvenements(ActionEvent event) {
         try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("readEvenement.fxml"));

            Parent root =top.load();
                   ReadEvenementController dpc=top.getController();
                    
                    
                    txtportee.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToSatisticsE(ActionEvent event) {
         try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("staticEvenement.fxml"));

            Parent root =top.load();
                   StaticEvenementController dpc=top.getController();
                    
                    
                    txtportee.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToFront(ActionEvent event) {
         try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("front.fxml"));

            Parent root =top.load();
                   FrontController dpc=top.getController();
                    
                    
                    txtportee.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void addEvent(ActionEvent event) {
        
        ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
        
        System.out.println(us);
        String descriptionEvenement = txtnom.getText();
     String lieuEvenement =txtlieu.getText();
     LocalDate dateEvenement= dateP.getValue();
     LocalTime heureEvenement =timeheure.getValue();
     String porteeEvenement =txtportee.getText();
     Float prixEvenement=0.0f;
       
      Evenement std = new Evenement();
   std.setDescriptionEvenement(descriptionEvenement);
   std.setLieuEvenement(lieuEvenement);
   std.setDateEvenement(dateEvenement);
   std.setHeureEvenement(heureEvenement);
   std.setPorteeEvenement(porteeEvenement);
   std.setPrixEvenement(prixEvenement);
   std.setid(us);
   std.setImage(fich);
    System.out.println(lieuEvenement);
        System.out.println(dateEvenement);
        System.out.println(heureEvenement);
                System.out.println(fich);
            
   
       
  ServiceEvenement ser = new ServiceEvenement();
        try {
            ser.ajouter(std);
        } catch(SQLException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void home(MouseEvent event) {
        loadStage("Home.fxml");
    }

    private void loadStage(String fxml) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));

            rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
    @FXML
    private void myevents(MouseEvent event) {
        loadStage("readEvenement.fxml");
    }
    @FXML
    private void statistiques(ActionEvent event) {
        loadStage("staticEvenement.fxml");
    }
    @FXML
    private void eventlist(ActionEvent event) {
        loadStage("front.fxml");
    }  
   
   

}
