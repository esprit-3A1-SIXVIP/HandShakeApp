/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Services.ServiceEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class EvenementController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addEvent(ActionEvent event) {
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
  ServiceEvenement ser = new ServiceEvenement();
        try {
            ser.ajouter(std);
        } catch(SQLException ex){
            System.out.println(ex);
        }
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
    
}
